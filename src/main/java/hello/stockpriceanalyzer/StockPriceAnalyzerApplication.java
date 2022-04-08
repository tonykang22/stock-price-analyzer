package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockPriceAnalyzerApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(StockPriceAnalyzerApplication.class, args);
	}
}
