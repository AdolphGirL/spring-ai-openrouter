package com.reyes.ai;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.reyes.ai.service.WeatherService;

@SpringBootApplication
public class SpringAiOpenrouterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiOpenrouterApplication.class, args);
	}
	
	/**
	 * mcp tool 註冊到 mcp server
	 * @param weatherService
	 * @return
	 */
	@Bean
	public ToolCallbackProvider weatherTools(WeatherService weatherService) {
		return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
	}
	
}
