package com.payMyBuddy.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.payMyBuddy.App.Service"})
public class PaymybuddyappApplication {

	public static void main(String[] args) {
		System.out.print(" ################ main");
		SpringApplication.run(PaymybuddyappApplication.class, args);
	}

}
