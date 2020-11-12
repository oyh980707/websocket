package com.loveoyh.websocket.common.domain;

/**
 * 消息类型定义枚举
 * @Created by oyh.Jerry to 2020/11/10 11:19
 */
public enum  MessageType {

	NOTICE("notice"),
	LOG("log"),
	INFO("info"),
	CHAT("chat"),
	ADVERTISE("advertise");
	
	private String type;
	
	MessageType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
