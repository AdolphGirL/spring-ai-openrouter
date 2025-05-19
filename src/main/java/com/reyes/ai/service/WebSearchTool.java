package com.reyes.ai.service;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebSearchTool {
	
	@Value("${spring.ai.web-search.api-key}")
	private String apiKey;
	
	@Value("${spring.ai.web-search.endpoint}")
	private String searchEndpoint;

	private final WebClient webClient = WebClient.builder().build();

	/** 把標註的方法或類別暴露給 AI Agent 作為工具使用。 */
	@Tool(description = "網路收尋即時資訊")
	public String webSearch(@ToolParam(description = "搜尋關鍵字") String query,
			@ToolParam(description = "傳回結果數（1-10）") Integer numResults) {
		
		/** https://serpapi.com/search-api，q: Required，api_key: Required */
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(searchEndpoint).queryParam("q", query)
				.queryParam("num", numResults != null ? numResults : 3).queryParam("api_key", apiKey);
		
		log.info("[+] webSearch，query param: {}，nums: {} ", query, numResults);
		
		return webClient.get().uri(uriBuilder.build().toUri()).retrieve()
					.bodyToMono(SearchResult.class)
					.map(this::formatResults).block();
	}
	
	private String formatResults(SearchResult result) {
		StringBuilder sb = new StringBuilder();
		for (SearchResult.Item item : result.items) {
			sb.append("標題：").append(item.title)
			  .append("\n連結：").append(item.link)
			  .append("\n摘要：").append(item.snippet).append("\n\n");
		}
		return sb.toString();
	}

	public static class SearchResult {
		@JsonProperty("organic_results")
		List<Item> items;

		public static class Item {
			String title;
			String link;
			String snippet;
		}
	}

}
