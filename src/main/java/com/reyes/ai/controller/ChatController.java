package com.reyes.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private final ChatClient chatClient;

	public ChatController(ChatClient chatClient) {
		this.chatClient = chatClient;
	}

	@PostMapping("/ask")
	public String chat(@RequestBody String userInput) {
		return chatClient
				.prompt(userInput)
				.advisors(new SimpleLoggerAdvisor())
				.call().content();
	}

}
