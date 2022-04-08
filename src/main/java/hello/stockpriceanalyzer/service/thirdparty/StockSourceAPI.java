package hello.stockpriceanalyzer.service.thirdparty;

import hello.stockpriceanalyzer.dto.StockDto;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface StockSourceAPI {

    StockDto provideStockInformation(String stockSymbol) throws IOException, ParseException;
}
