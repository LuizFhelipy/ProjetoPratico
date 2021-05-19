package br.com.stock.quote.manager.service;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.stock.quote.manager.model.dto.StockDto;

@Service
public class StockService {

	private String defaultUrl = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();
	
	Logger log = LoggerFactory.getLogger(StockService.class);

	@Cacheable(value="liststock")
	public List<StockDto> listAll() {
		log.info("lista de stock");
		String url = defaultUrl + "/stock";

		StockDto[] stockDto = restTemplate.getForObject(url, StockDto[].class);

		return Arrays.asList(stockDto);
	}
	
	@Cacheable(value="stock")
	public StockDto GetById(String id) {
		log.info("apenas um stock");
		String url = defaultUrl + "/stock" + "/" + id;

		StockDto stockDto = restTemplate.getForObject(url, StockDto.class);

		return stockDto;
	}
	
	public void notification() {
		log.info("notificação");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		JSONObject data = new JSONObject();
		data.put("host", "localhost");
		data.put("port", "8081");
		HttpEntity<String> request = new HttpEntity<String>(data.toString(), headers);
		restTemplate.postForObject(defaultUrl + "/notification", request, String.class);
	}

}
