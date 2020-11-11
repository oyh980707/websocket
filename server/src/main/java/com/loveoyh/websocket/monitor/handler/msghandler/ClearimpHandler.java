package com.loveoyh.websocket.monitor.handler.msghandler;

import com.alibaba.fastjson.JSONObject;
import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.common.domain.MessageModule;
import com.loveoyh.websocket.common.domain.MessageType;
import com.loveoyh.websocket.monitor.handler.MessageHandler;
import com.loveoyh.websocket.websocket.WebSocketServer;

import java.io.IOException;

/**
 * @Created by oyh.Jerry to 2020/11/11 09:21
 */
public class ClearimpHandler implements MessageHandler {
	
	@Override
	public boolean handle(WebSocketServer socket, CommonMsg commonMsg) {
		
		if(MessageType.INFO.getType().equals(commonMsg.getHeaders().get(MessageHeaders.TYPE))
				&& MessageModule.MONITOR_CLEARIMP.getModule().equals(commonMsg.getHeaders().get(MessageHeaders.MODULE))){
			try {
				socket.getSession().getBasicRemote().sendText(JSONObject.toJSONString(commonMsg));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
}
