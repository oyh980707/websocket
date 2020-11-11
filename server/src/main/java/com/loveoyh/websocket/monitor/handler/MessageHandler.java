package com.loveoyh.websocket.monitor.handler;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.websocket.WebSocketServer;

/**
 * @Created by oyh.Jerry to 2020/11/10 16:06
 */
public interface MessageHandler {
	
	boolean handle(WebSocketServer socket, CommonMsg commonMsg);
	
}
