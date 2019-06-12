package com.stream.flux.controller;

import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stream.flux.domain.StreamMe;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/rest")
public class StreamMeResource {
	private static final int STREAM_INTERVAL_SECONDS_VALUE = 1;
	private Supplier<String> randomStringSupplier = () -> UUID.randomUUID().toString();
	private Supplier<Flux<StreamMe>> streamMeStreamSupplier = () ->  {
		return Flux.fromStream(Stream.generate(() -> {
			return new StreamMe(randomStringSupplier.get(),randomStringSupplier.get());
		}));
	};
	private Supplier<Flux<Long>> intervalFlux = () -> Flux.interval(Duration.ofSeconds(STREAM_INTERVAL_SECONDS_VALUE));

	@GetMapping(value = "/everything", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<StreamMe> getEverything() {
		return Flux.zip(intervalFlux.get(), streamMeStreamSupplier.get())
					.map(Tuple2::getT2);
	}
}
