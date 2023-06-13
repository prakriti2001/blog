package com.task2.blogapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;

    public BlogAppApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("gigi"));
    }
}
