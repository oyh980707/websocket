package com.loveoyh.websocket.common.domain;

/**
 * 消息类型定义枚举
 * @Created by oyh.Jerry to 2020/11/10 11:19
 */
public enum  MessageType {

	ERROR_LOG("error_log"),
	INFO("info");
	
	private String type;
	
	MessageType(String type){
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
