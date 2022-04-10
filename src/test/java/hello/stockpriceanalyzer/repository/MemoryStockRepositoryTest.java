package hello.stockpriceanalyzer.repository;

import hello.stockpriceanalyzer.domain.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryStockRepositoryTest {

    MemoryStockRepository repository = new MemoryStockRepository();
    String stockSymbol;
    Map<Long, Double> stockInfo;
    Stock stock;

    @BeforeEach
    void createStock() {
        stockSymbol = "AAPL";
        stockInfo = new LinkedHashMap<>();
        stockInfo.put(1620653400L, 126.85);
        stockInfo.put(1620739800L, 125.91);
        stockInfo.put(1620826200L, 122.77);
        stockInfo.put(1620912600L, 124.97);

        stock = new Stock(stockSymbol, stockInfo);
    }

    @Test
    @DisplayName("Stock 저장하기")
    void saveStock() {
        // given
        Stock newStock = stock;

        // when
        Stock savedStock = repository.saveStock(newStock);

        // then
        assertEquals(savedStock, newStock);
    }

    @Test
    @DisplayName("Symbol로 찾기")
    void test() {
        // given
        String findSymbol = "AAPL";
        Stock savedStock = repository.saveStock(this.stock);

        // when
        Optional<Stock> findStock = repository.findByStockSymbol(findSymbol);

        // then
        assertEquals(findStock.get(), savedStock);
    }

}