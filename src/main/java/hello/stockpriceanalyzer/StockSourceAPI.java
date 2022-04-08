package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockSourceAPI {

    Stock provideStockInformation(String stockSymbol) throws IOException, ParseException;
}
