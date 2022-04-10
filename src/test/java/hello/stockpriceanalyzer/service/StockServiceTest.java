package hello.stockpriceanalyzer.service;

import hello.stockpriceanalyzer.domain.MaxProfit;
import hello.stockpriceanalyzer.domain.Stock;
import hello.stockpriceanalyzer.repository.MemoryStockRepository;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

    @Autowired
    StockService service;

    @Autowired
    MemoryStockRepository repository;
    String symbol;
    Map<Long, Double> stockInfo;
    Stock stock;

    @BeforeAll
    void loadStockData() {
        symbol = "AAPL";
        stockInfo = new LinkedHashMap<>();
        stockInfo.put(1620653400L, 126.85);
        stockInfo.put(1620739800L, 125.91);
        stockInfo.put(1620826200L, 122.77);
        stockInfo.put(1620912600L, 124.97);
        stockInfo.put(1620999000L, 127.45);

        stock = new Stock(symbol, stockInfo);
    }

    @Test
    @DisplayName("올바른 계산")
    void profitCalculation() throws IOException, ParseException {
        // given
        double profit = 4.68;
        double percentage = 3.81;

        // when
        MaxProfit maxProfit = service.calculateProfit(stock);

        // then
        assertEquals(maxProfit.getProfit(), profit);
        assertEquals(maxProfit.getPercentage(), percentage);
    }

    @Test
    @DisplayName("또 다른 경우 계산")
    void profitCalculation2() throws IOException, ParseException {
        // given
        stockInfo.put(1621099000L, 122.44);
        double profit = 4.68;
        double percentage = 3.81;

        // when
        MaxProfit maxProfit = service.calculateProfit(stock);

        // then
        assertEquals(maxProfit.getProfit(), profit);
        assertEquals(maxProfit.getPercentage(), percentage);
    }

    @Test
    @DisplayName("Symbol로 검색")
    void findBySymbol() throws IOException, ParseException {
        // given
        String findSymbol = "AAPL";

        // when
        Stock findStock = service.findByStockSymbol(findSymbol);

        // then
        assertEquals(findStock.getStockSymbol(), findSymbol);
    }

    @Test
    @DisplayName("저장소에서 가져오기")
    void findBySymbolMemory() throws IOException, ParseException {
        // given
        String findSymbol = "AAPL";
        Stock savedStock = repository.saveStock(this.stock);

        // when
        Stock findStockOnce = service.findByStockSymbol(findSymbol);
        Stock findStockAgain = service.findByStockSymbol(findSymbol);

        // then
        assertEquals(findStockAgain.getStockSymbol(), findSymbol);
    }

}
