package com.reyes.ai.config;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.reyes.ai.service.WebSearchTool;

@Configuration
public class McpServerWebMvcConfig {
	
	/**
	 * 註冊 server 提供 tool
	 * toolObjects 可接收多個工具
	 * @param webSearchTool
	 * @return
	 */
	@Bean
	ToolCallbackProvider getToolCallbacks(WebSearchTool webSearchTool) {
		return MethodToolCallbackProvider.builder().toolObjects(webSearchTool).build();
	}
	
}
