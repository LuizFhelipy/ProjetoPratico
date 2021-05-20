package br.com.stock.quote.manager.controller;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.stock.quote.manager.model.dto.StockDto;
import br.com.stock.quote.manager.service.StockService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class QuoteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StockService stockService;
	
	@Test
	public void registerTest() throws Exception {
		StockDto stock = new StockDto();
		stock.setId("petr4");
		
		Mockito.when(stockService.GetById("petr4")).thenReturn(stock);
		
		JSONObject quotes = new JSONObject();
		quotes.put("2021-05-19", "120");
		quotes.put("2021-05-20", "240");
		JSONObject data = new JSONObject();
		data.put("id", "petr4");
		data.put("quotes", quotes);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/quote")
				.content(data.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(201))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("quotes")));;
	}
	
	@Test
	public void registerTestInvalido() throws Exception {
		Mockito.when(stockService.GetById("postoipiranga")).thenReturn(null);
		
		JSONObject quotes = new JSONObject();
		quotes.put("2021-01-10", "250");
		quotes.put("2021-01-11", "350");
		JSONObject data = new JSONObject();
		data.put("id", "postoipiranga");
		data.put("quotes", quotes);
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/quote")
				.content(data.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(404));
	}
	
	@Test
	public void listAllTest() throws Exception {
		StockDto stock1 = new StockDto();
		stock1.setId("petr4");
		
		StockDto stock2 = new StockDto();
		stock2.setId("vale5");
		
		List<StockDto> stocks = new ArrayList<>();
		stocks.add(stock1);
		stocks.add(stock2);
		
		Mockito.when(stockService.listAll()).thenReturn(stocks);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/quote")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	void listByIdTest() throws Exception{
		StockDto stock = new StockDto();
		stock.setId("petr4");
		
		Mockito.when(stockService.GetById("petr4")).thenReturn(stock);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/quote/petr4")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("quotes")));
	}
	
	@Test
	public void listByIdTestInvalido() throws Exception {
		
		Mockito.when(stockService.GetById("postoipiranga")).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/quote/postoipiranga")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(404));
	}

}
