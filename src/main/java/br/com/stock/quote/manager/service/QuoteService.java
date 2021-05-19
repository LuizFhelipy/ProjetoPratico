package br.com.stock.quote.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stock.quote.manager.model.Quote;
import br.com.stock.quote.manager.repository.QuoteRepository;

@Service
public class QuoteService {
	
	Logger log = LoggerFactory.getLogger(QuoteService.class);
	
	@Autowired
	QuoteRepository quoteRepository;
	
	public void saveQuote(List<Quote> quotes) {
		log.info("saveQuote");
		String response = "Salvando Quotes";
		log.debug(response);
		quoteRepository.saveAll(quotes);
	}
	
	public List<Quote> findByStockId(String stockId) {
		
		log.debug("findByStockId {}", stockId);
		String response = "Buscando pelo id " + stockId;
		log.warn(response);
		return quoteRepository.findByStockId(stockId);
	}
}
