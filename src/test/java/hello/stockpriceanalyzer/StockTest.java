package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    @DisplayName("요청한 데이터 확인")
    void checkRequestedData() throws IOException, ParseException {
        // given
        String symbol = "AAPL";
        Stock stock = new Stock(symbol);

        // when
        Map<Long, Double> stockPrice = stock.provideStockInformation();

        // then
        assertFalse(stockPrice.isEmpty());
    }

}