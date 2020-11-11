package com.loveoyh.websocket.websocket;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.monitor.handler.MessageHandler;
import com.loveoyh.websocket.monitor.handler.MessageHandlerChain;
import com.loveoyh.websocket.monitor.handler.MessageHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 后台管理系统的推送
 */
@ServerEndpoint(value = "/websocket/{id}")
@Component
public class WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    
    private static final String SUBSCRIBE_POSTFIX = "subscription";
    private static final String CANCEL_POSTFIX = "cancel";
    
    /** 记录每个客户端连接时，产生对应的WebSocketServer对象。concurrent包的线程安全Set */
    private final static Map<String, List<WebSocketServer>> webSocketSet = new ConcurrentHashMap<>();
    
    /** 每个客户端连接时，产生连接会话。需要通过它来给对应的客户端发送数据 */
    private Session session;
	/** 用户id唯一标识 */
	private String id;
	/** 保存上次活跃状态时间，时间戳形式 */
	private Long lastActiveTime;
	/** 消息处理器 */
	private final MessageHandlerChain chain = new MessageHandlerChain();
	
	@OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        this.session = session;
        this.id = id;
        this.lastActiveTime = System.currentTimeMillis();
		
        LOGGER.info("[" + id + "] connected");
        //加入set中
        if(webSocketSet.containsKey(id)){
            webSocketSet.get(id).add(this);
            return ;
        }
        List<WebSocketServer> l = new ArrayList<>();
        l.add(this);
        webSocketSet.put(id, l);
    }
    
    @OnClose
    public void onClose(@PathParam("id") String id, Session session) throws IOException {
        LOGGER.info("[" + id + "] removed");
        //从set中删除
        webSocketSet.get(id).remove(this);

        if(webSocketSet.get(id).isEmpty()){
            webSocketSet.remove(id);
        }
		session.close();
    }
    
    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        LOGGER.info("A message from the client:" + message);
		this.lastActiveTime = System.currentTimeMillis();
		
		if(message.endsWith(SUBSCRIBE_POSTFIX)){
			MessageHandler handler = MessageHandlerFactory.getHandler(message.substring(0, message.lastIndexOf(".")));
			if(null != handler) {
				this.chain.addHandler(handler);
			}
		}else if (message.endsWith(CANCEL_POSTFIX)){
			MessageHandler handler = MessageHandlerFactory.getHandler(message.substring(0, message.lastIndexOf(".")));
			if(null != handler) {
				this.chain.removeHandler(handler);
			}
		}else{
			this.session.getBasicRemote().sendText("HeartBeat");
		}
    }
    
    @OnError
    public void onError(@PathParam("id") String id, Throwable error, Session session) throws IOException {
        LOGGER.error("[" + id + "] disconnected due to error : " + error.getLocalizedMessage());
        //
        webSocketSet.get(id).remove(this);
        if(webSocketSet.get(id).isEmpty()){
            webSocketSet.remove(id);
        }
        session.close();
    }
    
    public void sendMessage(CommonMsg commonMsg) throws IOException {
		this.chain.handle(this, commonMsg);
    }
    
    public static void webSocketScanAndClear(){
		Long now = System.currentTimeMillis();
		
		webSocketSet.values().stream().forEach(list -> {
			for (int i = 0; i < list.size(); i++) {
				WebSocketServer ws = list.get(i);
				if((now - ws.lastActiveTime) > 1000 * 10){
					LOGGER.info("[" + ws.id + "] removed due to timeout.");
					list.remove(ws);
					try {
						ws.session.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public static Map<String, List<WebSocketServer>> getSocket(){
		return webSocketSet;
	}
	
	public Session getSession(){
		return this.session;
	}
 
}
