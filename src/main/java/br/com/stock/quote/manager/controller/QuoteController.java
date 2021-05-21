package br.com.stock.quote.manager.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.stock.quote.manager.config.validacao.ErroDeFormularioDto;
import br.com.stock.quote.manager.form.StockQuoteForm;
import br.com.stock.quote.manager.model.Quote;

import br.com.stock.quote.manager.model.dto.StockDto;
import br.com.stock.quote.manager.model.dto.StockQuoteDto;
import br.com.stock.quote.manager.service.QuoteService;
import br.com.stock.quote.manager.service.StockService;

@RestController
@RequestMapping("/quote")
public class QuoteController {

	@Autowired
	QuoteService quoteService;

	@Autowired
	StockService stockService;

	Logger log = LoggerFactory.getLogger(QuoteController.class);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping
	public ResponseEntity<StockQuoteDto> register(@RequestBody @Valid StockQuoteForm form, UriComponentsBuilder uriBuilder) {
		
		StockDto stockDto = stockService.GetById(form.getId());
		if(stockDto == null) {
			log.error("Stock não existe");
			return new ResponseEntity(new ErroDeFormularioDto("id","Não foi encontrado Stock com o id:"+form.getId() +" para inserir os Quotes"), HttpStatus.NOT_FOUND);
		}
		quoteService.saveQuote(form.convert());
		URI uri = uriBuilder.path("/quote/{id}").buildAndExpand(form.getId()).toUri();
		
		List<Quote> quotes = quoteService.findByStockId(form.getId());
		log.info("quote criada");
		return ResponseEntity.created(uri).body(new StockQuoteDto(form.getId(), quotes));
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping
	public ResponseEntity<List<StockQuoteDto>> listAll() {
		List<StockDto> stocks = stockService.listAll();
		List<StockQuoteDto> stockQuote = stocks.stream().map(stock -> {
			List<Quote> quotes = quoteService.findByStockId(stock.getId());
			return new StockQuoteDto(stock.getId(), quotes);
		}).collect(Collectors.toList());

		if (stockQuote.isEmpty()) {
			log.error("Não foi encontrado lista de StockQuotes");
			return new ResponseEntity(new ErroDeFormularioDto("id","Não foi encontrado StockQuotes"), HttpStatus.NOT_FOUND);
		} else {
			log.info("Lista de stockQuotes encontrada");
			return ResponseEntity.status(200).body(stockQuote);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{id}")
	public ResponseEntity<StockQuoteDto> listById(@PathVariable String id) {
		StockDto stock = stockService.GetById(id);
		List<Quote> quotes = quoteService.findByStockId(id);

		if (stock == null) {
			log.error("Não foi encontrado StockQuotes com o id: " + id);
			return new ResponseEntity(new ErroDeFormularioDto("stock","Não foi encontrado Stock com o id: "+ id), HttpStatus.NOT_FOUND);
		} else {
			log.info("stockQuotes com o id: " + id + " encontrada");
			return ResponseEntity.ok(new StockQuoteDto(id, quotes));
		}

	}
	
	

}
