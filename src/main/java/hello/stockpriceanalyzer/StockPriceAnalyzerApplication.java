package hello.stockpriceanalyzer;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StockPriceAnalyzerApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(StockPriceAnalyzerApplication.class, args);
	}
}
