package br.com.stock.quote.manager.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


import br.com.stock.quote.manager.model.Quote;

public class QuoteDto {
	
	
	private LocalDate date;
	private BigDecimal price;
	
	public QuoteDto(Quote quote) {
		
		this.date = quote.getDate();
		this.price = quote.getPrice();
	}
	
	public LocalDate getDate() {
		return date;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
}
