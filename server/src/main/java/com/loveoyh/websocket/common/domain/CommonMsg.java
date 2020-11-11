package com.loveoyh.websocket.common.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * common message
 * @Created by oyh.Jerry to 2020/11/09 14:49
 */
public class CommonMsg<T> implements Serializable{
	
	private final MessageHeaders headers;
	
	private final T body;
	
	public CommonMsg() {
		this(null, null);
	}
	
	public CommonMsg(T body) {
		this(null, body);
	}
	
	public CommonMsg(Map<String, String> headers) {
		this(headers, null);
	}
	
	public CommonMsg(Map<String, String> headers, T body) {
		this.body = body;
		
		MessageHeaders tempHeaders = new MessageHeaders(headers);
		this.headers = tempHeaders;
	}
	
	public MessageHeaders getHeaders() {
		return this.headers;
	}
	
	public T getBody() {
		return this.body;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("<");
		
		MessageHeaders headers = getHeaders();
		builder.append(headers);
		
		T body = getBody();
		if (body != null) {
			builder.append(body);
			builder.append(',');
		}
		
		builder.append('>');
		return builder.toString();
	}
	
	// Static builder methods
	
	public static BodyBuilder instance() {
		return new DefaultBuilder();
	}
	
	// interface or class
	
	public interface HeadersBuilder<B extends HeadersBuilder<B>>{
		B header(String headerName, String headerValue);
		
		<T> CommonMsg<T> build();
	}
	
	public interface BodyBuilder extends HeadersBuilder<BodyBuilder> {
		<T> CommonMsg<T> body(T body);
	}
	
	private static class DefaultBuilder implements BodyBuilder {
		
		private final MessageHeaders headers = new MessageHeaders();
		
		@Override
		public BodyBuilder header(String headerName, String headerValue) {
			this.headers.put(headerName, headerValue);
			return this;
		}
		
		@Override
		public <T> CommonMsg<T> build() {
			return body(null);
		}
		
		@Override
		public <T> CommonMsg<T> body(T body) {
			return new CommonMsg<>(this.headers, body);
		}
	}
	
}
