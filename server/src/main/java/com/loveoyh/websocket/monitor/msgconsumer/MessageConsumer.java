package com.loveoyh.websocket.monitor.msgconsumer;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.websocket.WebSocketServer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Created by oyh.Jerry to 2020/11/11 11:28
 */
@Component
public class MessageConsumer {

	public void send(CommonMsg commonMsg){
		Map<String, List<WebSocketServer>> socket = WebSocketServer.getSocket();
		
		// broadcast
		if(null == commonMsg.getHeaders().get(MessageHeaders.AUTH)){
			socket.values().stream().forEach(item -> {
				item.stream().forEach(one -> {
					try {
						one.sendMessage(commonMsg);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
			});
		}

		// unicast
		else {
			String uid = commonMsg.getHeaders().get(MessageHeaders.AUTH);
			socket.get(uid).stream().forEach(one -> {
				try {
					one.sendMessage(commonMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		
	}

}
