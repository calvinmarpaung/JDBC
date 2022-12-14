package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.springboot"})
public class SpringbootRestOnlineStore {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestOnlineStore.class, args);
    }
}
