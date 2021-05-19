package br.com.stock.quote.manager.form;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.stock.quote.manager.model.Quote;

public class StockQuoteForm {
	@NotEmpty
	@NotNull
	private String id;
	@NotEmpty
	@NotNull
	Map<String,String> quotes = new HashMap<String,String>();
	
	public String getId() {
		return id;
	}

	public Map<String, String> getQuotes() {
		return quotes;
	}

	public List<Quote> convert() {
		List<Quote> quotesList = new ArrayList<>();

        for (Map.Entry<String, String> quote : this.quotes.entrySet()) {
            LocalDate date = LocalDate.parse(quote.getKey());
            BigDecimal price = new BigDecimal(quote.getValue());

            quotesList.add(new Quote(this.id, date, price));
        }

        return quotesList;
	}
}
