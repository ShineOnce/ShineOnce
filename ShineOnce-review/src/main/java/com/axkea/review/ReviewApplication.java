package com.axkea.review;

<<<<<<< HEAD
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
=======
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
>>>>>>> 507f81ae7f9134f92bc13dca79737eb98772735c

@SpringBootApplication
@EnableDiscoveryClient
public class ReviewApplication {
<<<<<<< HEAD
    public static void main(String[] args) {
        SpringApplication.run(ReviewApplication.class,args);
    }
=======

    public static void main(String[] args) {
        SpringApplication.run(ReviewApplication.class,args);
    }

>>>>>>> 507f81ae7f9134f92bc13dca79737eb98772735c
}
