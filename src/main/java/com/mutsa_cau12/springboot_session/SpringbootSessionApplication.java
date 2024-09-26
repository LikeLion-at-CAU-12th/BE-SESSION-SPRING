package com.mutsa_cau12.springboot_session;

import com.mutsa_cau12.springboot_session.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSessionApplication.class, args);
	}
}
