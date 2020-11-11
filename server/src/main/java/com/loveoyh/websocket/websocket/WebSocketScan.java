package com.loveoyh.websocket.websocket;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.common.domain.MessageType;
import com.loveoyh.websocket.monitor.msgconsumer.MessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Created by oyh.Jerry to 2020/11/04 13:26
 */
@Component
public class WebSocketScan {
	
	@Autowired
	private MessageConsumer consumer;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketScan.class);
	
//	@Scheduled(cron="0/10 * *  * * ? ")
//	public void scan(){
//		LOGGER.info("start scanning...");
//		WebSocketServer.webSocketScanAndClear();
//	}
	
	@Scheduled(cron="0/1 * *  * * ? ")
	public void test(){
		consumer.send(CommonMsg.instance().header(MessageHeaders.TYPE, MessageType.ERROR_LOG.getType())
				.header(MessageHeaders.MODULE,"monitor.errorinfo")
				.body("Hello World!"));
	}
	
}
