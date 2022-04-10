package hello.stockpriceanalyzer.controller;

import hello.stockpriceanalyzer.domain.MaxProfit;
import hello.stockpriceanalyzer.domain.Stock;
import hello.stockpriceanalyzer.service.StockService;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class StockController {

    private final StockService stockService = new StockService();

    @GetMapping("/stocks/profit/{symbol}")
    public ModelAndView process(@PathVariable String symbol) throws IOException, ParseException {
        Stock findStock = stockService.findByStockSymbol(symbol);
        MaxProfit maxProfit = stockService.calculateProfit(findStock);
        return new ModelAndView("response/profit")
                .addObject(maxProfit);
    }
}
