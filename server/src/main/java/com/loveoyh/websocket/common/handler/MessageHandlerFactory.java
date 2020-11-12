package com.loveoyh.websocket.common.handler;

import com.loveoyh.websocket.common.domain.MessageTag;
import com.loveoyh.websocket.common.handler.msghandler.ClearimpHandler;
import com.loveoyh.websocket.common.handler.msghandler.GlobalMsgHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by oyh.Jerry to 2020/11/11 09:58
 */
public class MessageHandlerFactory {
	
	private static final Map<String, MessageHandler> CACHED_HANDLER = new HashMap<>();
	
	static {
		CACHED_HANDLER.put(MessageTag.GLOBAL.getTag(), GlobalMsgHandler.getInstance());
		CACHED_HANDLER.put(MessageTag.SETTLEMENT_CLEARIMP.getTag(),ClearimpHandler.getInstance());
	}
	
	public static MessageHandler getHandler(String type) {
		if (type == null || type.isEmpty()) {
			return null;
		}
		return CACHED_HANDLER.get(type);
	}
	
	
}
