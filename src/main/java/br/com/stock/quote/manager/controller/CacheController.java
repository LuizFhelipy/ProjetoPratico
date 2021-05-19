package br.com.stock.quote.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stock.quote.manager.service.StockService;

@RestController
@RequestMapping("/stockcache")
public class CacheController {
	
	Logger log = LoggerFactory.getLogger(CacheController.class);
	
	@Autowired
	public CacheController(StockService stockService) {
		stockService.notification();
	}
	
	@DeleteMapping
	@Transactional
	@Caching(evict = {@CacheEvict(value="liststock", allEntries = true), @CacheEvict(value="stock", allEntries= true)})
	public ResponseEntity<?> cleanCache(){
		log.info("cleanCache");
		return ResponseEntity.status(204).build();
	}
}
