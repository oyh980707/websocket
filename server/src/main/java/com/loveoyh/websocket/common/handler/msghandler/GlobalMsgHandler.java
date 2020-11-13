package com.loveoyh.websocket.common.handler.msghandler;

import com.alibaba.fastjson.JSONObject;
import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.common.domain.MessageTag;
import com.loveoyh.websocket.common.handler.MessageHandler;
import com.loveoyh.websocket.websocket.WebSocketServer;

import java.io.IOException;

/**
 * global message handler
 * 双重检查锁+防止指令重排的单例模式
 * @Created by oyh.Jerry to 2020/11/12 09:47
 */
public class GlobalMsgHandler implements MessageHandler {
	
	private static volatile GlobalMsgHandler globalMsgHandler;
	
	private GlobalMsgHandler(){
		if(null != globalMsgHandler){
			throw new RuntimeException("Two instance are not allowed to be created!");
		}
	}
	
	@Override
	public boolean handle(WebSocketServer socket, CommonMsg commonMsg) {
		if(MessageTag.GLOBAL.getTag().equals(commonMsg.getHeaders().get(MessageHeaders.TAG))){
			try {
				socket.getSession().getBasicRemote().sendText(JSONObject.toJSONString(commonMsg));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	public static GlobalMsgHandler getInstance(){
		if(null == globalMsgHandler){
			synchronized (GlobalMsgHandler.class){
				if(null == globalMsgHandler){
					globalMsgHandler = new GlobalMsgHandler();
				}
			}
		}
		return globalMsgHandler;
	}
	
}
