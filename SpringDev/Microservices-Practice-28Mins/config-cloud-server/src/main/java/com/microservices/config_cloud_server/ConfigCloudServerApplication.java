package com.microservices.config_cloud_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer // transforms into a spring cloud config server -> exposes rest endpoints, connect to git repo
@SpringBootApplication
public class ConfigCloudServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigCloudServerApplication.class, args);
	}
}