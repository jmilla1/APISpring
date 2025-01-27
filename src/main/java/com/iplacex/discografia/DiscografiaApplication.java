package com.iplacex.discografia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {
    "com.iplacex.discografia.Artista",
    "com.iplacex.discografia.Disco"
})
public class DiscografiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscografiaApplication.class, args);
	}

}
