package br.com.stock.quote.manager.model.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.stock.quote.manager.model.Quote;

public class StockQuoteDto {
	
	private String id;
	Map<String,String> quotes = new HashMap<String,String>();
	
	public StockQuoteDto(String stockId, List<Quote> quotes) {
		this.id = stockId;
		this.mapQuotes(quotes);
	}
	
	public String getId() {
		return id;
	}
	
	public Map<String, String> getQuotes() {
		return quotes;
	}
	
	public void mapQuotes(List<Quote> quotes) {
		quotes.forEach(quote -> {
			String date = quote.getDate().toString();
			String price = quote.getPrice().toBigInteger().toString();
			
			this.getQuotes().put(date, price);
		});
	}
	
	
}
