package hello.stockpriceanalyzer.controller;

import org.json.simple.parser.ParseException;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface StockController {

    ModelAndView process(String symbol) throws IOException, ParseException;
}
