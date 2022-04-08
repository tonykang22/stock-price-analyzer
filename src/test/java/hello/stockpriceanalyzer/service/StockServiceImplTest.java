package hello.stockpriceanalyzer.service;

import hello.stockpriceanalyzer.dto.MaxProfitDto;
import hello.stockpriceanalyzer.dto.StockDto;
import hello.stockpriceanalyzer.repository.MemoryStockRepository;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class StockServiceImplTest {

    @Autowired StockServiceImpl service;

    @MockBean
    private MemoryStockRepository repository;

    private String symbol;
    private Map<Long, Double> stockInfo;

    @BeforeAll
    void loadStockData() {
        symbol = "AAPL";
        stockInfo = new LinkedHashMap<>();
        stockInfo.put(1620653400L, 126.85);
        stockInfo.put(1620739800L, 125.91);
        stockInfo.put(1620826200L, 122.77);
        stockInfo.put(1620912600L, 124.97);
        stockInfo.put(1620999000L, 127.45);
    }

    @Test
    @DisplayName("올바른 계산")
    void profitCalculation() throws IOException, ParseException {
        // given
        StockDto stock = new StockDto(symbol, stockInfo);

        // when
        MaxProfitDto profit = service.calculateProfit(stock);

        // then
        assertEquals(profit.getProfit(), 4.68);
        assertEquals(profit.getPercentage(), 3.81);
    }

    @Test
    @DisplayName("Symbol로 검색")
    void findBySymbol() throws IOException, ParseException {
        // given
        StockDto stock = new StockDto(symbol, stockInfo);
        String symbol = "AAPL";

        // when
        when(repository.findByStockSymbol(symbol)).thenReturn(Optional.of(stock));
        Optional<StockDto> ss = repository.findByStockSymbol(symbol);

        // then
        assertEquals(ss.get().getStockSymbol(), symbol);
    }

}