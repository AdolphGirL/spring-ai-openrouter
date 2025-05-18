package com.reyes.ai.service;

import org.springframework.ai.tool.annotation.Tool;

public class WeatherService {
	
	/** @Tool 和 @ToolParam 定義mcp tool 行為和參數 */
	@Tool(description = "Get weather information by city name")
	public String getWeather(String cityName) {
		return "90.0";
	}
	
}
