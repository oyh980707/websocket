package com.loveoyh.websocket.common.domain;

/**
 * 消息模块定义枚举
 * @Created by oyh.Jerry to 2020/11/10 11:19
 */
public enum MessageModule {
	
	MONITOR_CLEARIMP("monitor.clearimp"),
	MONITOR_ERRORINFO("monitor.errorinfo");
	
	private String module;
	
	MessageModule(String module){
		this.module = module;
	}
	
	public String getModule() {
		return module;
	}
}
