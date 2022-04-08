package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockService {

    MaxProfit calculateProfit(String symbol) throws IOException, ParseException;

}
