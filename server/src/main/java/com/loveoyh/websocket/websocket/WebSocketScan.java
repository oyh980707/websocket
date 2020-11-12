package com.loveoyh.websocket.websocket;

import com.loveoyh.websocket.common.domain.CommonMsg;
import com.loveoyh.websocket.common.domain.MessageHeaders;
import com.loveoyh.websocket.common.domain.MessageTag;
import com.loveoyh.websocket.common.domain.MessageType;
import com.loveoyh.websocket.msgconsumer.MessageConsumer;
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
	
	/** scan every 10s */
	@Scheduled(cron="0/10 * *  * * ? ")
	public void scan(){
		LOGGER.info("start scanning...");
		WebSocketServer.webSocketScanAndClear();
	}
	
	/** test send message to tag of xxx every 5s */
	@Scheduled(cron="0/5 * *  * * ? ")
	public void test01(){
		consumer.send(CommonMsg.instance()
				.header(MessageHeaders.TYPE, MessageType.NOTICE.getType())
				.header(MessageHeaders.TAG, MessageTag.GLOBAL.getTag())
				.body("Hello World!"));
	}
	/** test send message to tag of xxx every 5s */
	@Scheduled(cron="0/5 * *  * * ? ")
	public void test02(){
		consumer.send(CommonMsg.instance()
				.header(MessageHeaders.TYPE, MessageType.INFO.getType())
				.header(MessageHeaders.TAG, MessageTag.SETTLEMENT_CLEARIMP.getTag())
				.body("This file is done."));
	}
	
}
