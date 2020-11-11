package com.loveoyh.websocket.monitor.handler;

import com.loveoyh.websocket.common.domain.MessageModule;
import com.loveoyh.websocket.monitor.handler.msghandler.ClearimpHandler;
import com.loveoyh.websocket.monitor.handler.msghandler.ErrorInfoHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Created by oyh.Jerry to 2020/11/11 09:58
 */
public class MessageHandlerFactory {
	
	private static final Map<String, MessageHandler> CACHED_HANDLER = new HashMap<>();
	
	static {
		CACHED_HANDLER.put(MessageModule.MONITOR_CLEARIMP.getModule(),new ClearimpHandler());
		CACHED_HANDLER.put(MessageModule.MONITOR_ERRORINFO.getModule(),new ErrorInfoHandler());
	}
	
	public static MessageHandler getHandler(String type) {
		if (type == null || type.isEmpty()) {
			return null;
		}
		return CACHED_HANDLER.get(type);
	}
	
	
}
