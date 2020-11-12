package com.loveoyh.websocket.common.handler.msghandler;

import com.alibaba.fastjson.JSONObject;
import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.common.domain.MessageTag;
import com.loveoyh.websocket.common.domain.MessageType;
import com.loveoyh.websocket.common.handler.MessageHandler;
import com.loveoyh.websocket.websocket.WebSocketServer;

import java.io.IOException;

/**
 * global message handler
 * @Created by oyh.Jerry to 2020/11/12 09:47
 */
public class GlobalMsgHandler implements MessageHandler {
	
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
	
}
