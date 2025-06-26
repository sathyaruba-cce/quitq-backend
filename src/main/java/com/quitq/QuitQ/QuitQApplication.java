package com.quitq.QuitQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication(scanBasePackages = "com.quitq")
@EnableJpaRepositories(basePackages = "com.quitq.repository")
@EntityScan(basePackages = "com.quitq.entity")
@EnableMethodSecurity
public class QuitQApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuitQApplication.class, args);
        System.out.println("QuitQ Project is Working");
    }
}
