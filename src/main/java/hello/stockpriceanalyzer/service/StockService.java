package hello.stockpriceanalyzer.service;

import hello.stockpriceanalyzer.dto.MaxProfitDto;
import hello.stockpriceanalyzer.dto.StockDto;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockService {

    StockDto findByStockSymbol(String symbol) throws IOException, ParseException;

    MaxProfitDto calculateProfit(StockDto stock) throws IOException, ParseException;
}
