package com.stream.flux.resource;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stream.flux.domain.StreamMe;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping("/rest/stream")
public class StreamMeResource {

	@GetMapping(value = "/everything", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<StreamMe> getEverything() {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<StreamMe> streamMeFlux = 
				Flux.fromStream(
							Stream.generate(() -> {
								return new StreamMe(UUID.randomUUID().toString(), 
										UUID.randomUUID().toString());
							}));
		return Flux.zip(interval, streamMeFlux)
				.map(Tuple2::getT2);
	}
}
