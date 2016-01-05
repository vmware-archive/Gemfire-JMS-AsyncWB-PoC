package io.pivotal.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/spring/context/spring-context.xml")
public class JMSApplication {

	@SuppressWarnings("unused")
	public static void main(String args[]) {

		ConfigurableApplicationContext context = SpringApplication.run(JMSApplication.class, args);
//		ApplicationContext context = new ClassPathXmlApplicationContext("spring/context/spring-context.xml");

	}

}
