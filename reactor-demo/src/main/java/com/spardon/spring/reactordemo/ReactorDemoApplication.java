package com.spardon.spring.reactordemo;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

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
				.publishOn(Schedulers.elastic())
				.map(i -> {
					log.info("Publish {}, {}", Thread.currentThread(), i);
					return i;
				})
				.doOnComplete(() -> log.info("Publisher COMPLETE Two"))
				.subscribeOn(Schedulers.single())
				.subscribe(i -> log.info("Subscribe{}: {}", Thread.currentThread(), i),
						e -> log.error("error {}", e.toString()),
						() -> log.info("Subscribe COMPLETE")
				);
		Thread.sleep(2000);
	}
}
