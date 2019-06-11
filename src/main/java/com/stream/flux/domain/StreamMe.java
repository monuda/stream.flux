package com.stream.flux.domain;

public class StreamMe {

	private String id;
	private String someName;
	
	public StreamMe() {
	}
	
	public StreamMe(String id, String someName) {
		this.id = id;
		this.someName = someName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSomeName() {
		return someName;
	}

	public void setSomeName(String someName) {
		this.someName = someName;
	}
}
