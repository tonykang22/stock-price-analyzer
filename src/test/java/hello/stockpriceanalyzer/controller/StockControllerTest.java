package hello.stockpriceanalyzer.controller;

import hello.stockpriceanalyzer.domain.Stock;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockControllerTest {

    @Autowired StockController controller;

    @Test
    @DisplayName("view 이름은 response/profit")
    void test() throws IOException, ParseException {
        // given
        String symbol = "AAPL";
        Map<Long, Double> stockInfo = new LinkedHashMap<>();
        Stock stock = new Stock(symbol, stockInfo);

        // when
        ModelAndView modelAndView = controller.process(symbol);

        // then
        assertEquals(modelAndView.getViewName(), "response/profit");
    }

}