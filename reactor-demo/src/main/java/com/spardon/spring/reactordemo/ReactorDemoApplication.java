package com.spardon.spring.reactordemo;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class ReactorDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Flux.range(1, 6).doOnRequest(n -> log.info("Request {} number", n))
				.doOnComplete(() -> log.info("Publisher COMPLETE One"))
				.map(i -> {
					log.info("Publish{}, {}", Thread.currentThread(), i);
					return i;
				})
				.doOnComplete(() -> log.info("Publisher COMPLETE Two"))
				.subscribe(i -> log.info("Subscribe{}: {}"))
	}
}
