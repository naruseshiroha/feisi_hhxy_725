package com.example;

import org.mybatis.spring.annotation.MapperScan;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.mapper")
@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {

		// Environment env = SpringApplication.run(DemoApplication.class,
		// args).getEnvironment();
		// LOG.info("Successful");
		// LOG.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
		SpringApplication.run(DemoApplication.class, args).getEnvironment();
	}

}
