package br.com.stock.quote.manager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.stock.quote.manager.model.Quote;
import br.com.stock.quote.manager.repository.QuoteRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class QuoteServiceTest {

	@Mock
	private QuoteRepository quoteRepository;
	
	@InjectMocks
	private QuoteService quoteService;
	
	@Test
	void testSalvarQuoteComSucesso() {
		
		List<Quote> quotes = new ArrayList<>();
		LocalDate date = LocalDate.now();
		BigDecimal price = BigDecimal.TEN;
		Quote quote1 = new Quote();
		Quote quote2 = new Quote();
		quote1.setStockId("petr4");
		quote1.setDate(date);
		quote1.setPrice(price);
		quote1.setStockId("petr4");
		quote1.setDate(date);
		quote1.setPrice(price);
		
		quotes.add(quote1);
		quotes.add(quote2);
		
		Mockito.when(quoteRepository.saveAll(quotes)).thenReturn(null);
		Mockito.when(quoteRepository.findByStockId("petr4")).thenReturn(quotes);
		quoteService.saveQuote(quotes);
		Assert.assertEquals(2,quoteService.findByStockId("petr4").size());
	}
	
	@Test
	void testFindByStockId() {
		List<Quote> quotes = new ArrayList<>();
		
		Mockito.when(quoteRepository.findByStockId("petr4")).thenReturn(quotes);
		
		Assert.assertEquals(0, quoteService.findByStockId("petr4").size());
	}

}
