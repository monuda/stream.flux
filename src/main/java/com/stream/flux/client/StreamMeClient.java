package com.stream.flux.client;

import org.springframework.web.reactive.function.client.WebClient;

import com.stream.flux.domain.StreamMe;

public class StreamMeClient {

	public static void main(String args[]) {
		WebClient.create("http://localhost:8082/rest/everything")
			.get()
			.uri("rest/everything")
			.retrieve()
			.bodyToFlux(StreamMe.class)
			.doOnNext(onNext -> System.out.println("ID - " + onNext.getId() + " :: Name - " + onNext.getSomeName()));
	}
}
