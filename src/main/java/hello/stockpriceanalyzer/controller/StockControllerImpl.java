package hello.stockpriceanalyzer.controller;

import hello.stockpriceanalyzer.dto.MaxProfitDto;
import hello.stockpriceanalyzer.dto.StockDto;
import hello.stockpriceanalyzer.service.StockService;
import hello.stockpriceanalyzer.service.StockServiceImpl;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class StockControllerImpl implements StockController {

    private final StockService stockService = new StockServiceImpl();

    @GetMapping("/stocks/profit/{symbol}")
    @Override
    public ModelAndView process(@PathVariable String symbol) throws IOException, ParseException {
        StockDto findStock = stockService.findByStockSymbol(symbol);
        MaxProfitDto profitInfo = stockService.calculateProfit(findStock);
        return new ModelAndView("response/profit")
                .addObject(profitInfo);
    }
}
