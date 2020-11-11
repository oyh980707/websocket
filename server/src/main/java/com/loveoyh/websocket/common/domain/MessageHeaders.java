package com.loveoyh.websocket.common.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created by oyh.Jerry to 2020/11/10 11:17
 */
public class MessageHeaders implements Map<String, String>, Serializable {
	
	final Map<String, String> headers;
	/**
	 * message type
	 */
	public static final String TYPE = "Type";
	/**
	 * message ownership module
	 */
	public static final String MODULE = "Module";
	/**
	 * message ownership module
	 */
	public static final String CONTENT = "Content";
	/**
	 * message auth
	 */
	public static final String AUTH = "Auth";
	
	public MessageHeaders() {
		this(new HashMap<>());
	}
	
	public MessageHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	@Override
	public String toString() {
		return this.headers.entrySet().stream()
				.map(entry -> {
					String values = entry.getValue();
					return entry.getKey() + ":" + "\"" + values + "\"";
				})
				.collect(Collectors.joining(", ", "[", "]"));
	}
	
	// getter and setter
	
	public void setType(String type){
		put(TYPE, type);
	}
	
	public String getType(){
		String value = get(TYPE);
		return (value != null ? value : "");
	}
	
	public void setModule(String module){
		put(MODULE, module);
	}
	
	public String getModule(){
		String value = get(MODULE);
		return (value != null ? value : "");
	}
	
	public void setContent(String content){
		put(CONTENT, content);
	}
	
	public String getContent(){
		String value = get(CONTENT);
		return (value != null ? value : "");
	}
	
	public void setAuth(String auth){
		put(AUTH, auth);
	}
	
	public String getAuth(){
		String value = get(AUTH);
		return (value != null ? value : "");
	}
	
	// Map implementation
	
	@Override
	public int size() {
		return this.headers.size();
	}
	
	@Override
	public boolean isEmpty() {
		return this.headers.isEmpty();
	}
	
	@Override
	public boolean containsKey(Object key) {
		return this.headers.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value) {
		return this.headers.containsValue(value);
	}
	
	@Override
	public String get(Object key) {
		return this.headers.get(key);
	}
	
	@Override
	public String put(String key, String value) {
		return this.headers.put(key, value);
	}
	
	@Override
	public String remove(Object key) {
		return this.headers.remove(key);
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends String> map) {
		this.headers.putAll(map);
	}
	
	@Override
	public void clear() {
		this.headers.clear();
	}
	
	@Override
	public Set<String> keySet() {
		return this.headers.keySet();
	}
	
	@Override
	public Collection<String> values() {
		return this.headers.values();
	}
	
	@Override
	public Set<Entry<String, String>> entrySet() {
		return this.headers.entrySet();
	}
	
}
