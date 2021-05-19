package br.com.stock.quote.manager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quote {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockId;
	private LocalDate date;
	private BigDecimal price;
	
public Quote() {
		
	}

	public Quote(String stockId, LocalDate date, BigDecimal price) {
		this.stockId = stockId;
		this.date = date;
		this.price = price;
		
	}

	public Long getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
