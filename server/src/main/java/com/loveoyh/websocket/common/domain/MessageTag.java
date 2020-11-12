package com.loveoyh.websocket.common.domain;

/**
 * 消息标签定义枚举
 * @Created by oyh.Jerry to 2020/11/10 11:19
 */
public enum MessageTag {
	
	GLOBAL("global"),
	SETTLEMENT_CLEARIMP("settlement.clearimp");
	
	private String tag;
	
	MessageTag(String tag){
		this.tag = tag;
	}
	
	public String getTag() {
		return tag;
	}
}
