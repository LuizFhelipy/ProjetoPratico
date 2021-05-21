package br.com.stock.quote.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class StockQuoteManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

}
