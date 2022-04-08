package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Map;

public interface StockSource {

    Map<Long, Double> provideStockInformation() throws IOException, ParseException;

}
