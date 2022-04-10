package hello.stockpriceanalyzer.service.thirdparty;

import hello.stockpriceanalyzer.domain.Stock;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockSourceAPI {

    Stock provideStockInformation(String stockSymbol) throws IOException, ParseException;
}
