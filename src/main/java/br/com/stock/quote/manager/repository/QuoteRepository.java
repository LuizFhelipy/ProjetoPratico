package br.com.stock.quote.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stock.quote.manager.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, Long>{

	List<Quote> findByStockId(String stockId);

}
