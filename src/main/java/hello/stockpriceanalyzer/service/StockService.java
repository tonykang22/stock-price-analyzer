package hello.stockpriceanalyzer.service;

import hello.stockpriceanalyzer.dto.MaxProfitDto;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockService {

    MaxProfitDto calculateProfit(String symbol) throws IOException, ParseException;

}
