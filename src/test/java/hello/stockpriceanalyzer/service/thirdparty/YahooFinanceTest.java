package hello.stockpriceanalyzer.service.thirdparty;

import hello.stockpriceanalyzer.domain.Stock;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class YahooFinanceTest {

    YahooFinance yahooFinance = new YahooFinance();

    @Test
    @DisplayName("연결해서 데이터 가져오기")
    void test() throws IOException, ParseException {
        // given
        String stockSymbol = "AAPL";

        // when
        Stock providedStock = yahooFinance.provideStockInformation(stockSymbol);

        // then
        assertEquals(providedStock.getStockSymbol(), stockSymbol);
    }

}