package com.reyes.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@Configuration
public class ChatClientConfiguration {
	
	
	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
	
	/**
	 * 建立一個RestClientCustomizerbean，將 Logbook 的攔截器加入到每個 RestClient
	 * 
	 * @param logbook
	 * @return
	 */
	@Bean
	RestClientCustomizer restClientCustomizer(Logbook logbook) {
		return restClientBuilder -> restClientBuilder
				.requestInterceptor(new LogbookClientHttpRequestInterceptor(logbook));
	}
	
}
