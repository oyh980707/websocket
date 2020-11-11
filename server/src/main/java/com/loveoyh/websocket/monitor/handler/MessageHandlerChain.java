package com.loveoyh.websocket.monitor.handler;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.websocket.WebSocketServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by oyh.Jerry to 2020/11/10 16:05
 */
public class MessageHandlerChain {
	private List<MessageHandler> msgHandlers = new ArrayList<>();

	public void addHandler(MessageHandler handler){
		this.msgHandlers.add(handler);
	}
	
	public void removeHandler(MessageHandler handler){
		this.msgHandlers.remove(handler);
	}
	
	public void handle(WebSocketServer socket, CommonMsg commonMsg){
		for (MessageHandler msgHandler : this.msgHandlers) {
			if(!msgHandler.handle(socket, commonMsg)){
				break;
			}
		}
	}
	
}
