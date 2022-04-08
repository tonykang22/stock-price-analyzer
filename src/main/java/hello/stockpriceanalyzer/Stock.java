package hello.stockpriceanalyzer;

import lombok.Getter;

import java.util.Map;

@Getter
public class Stock {

    private final String stockSymbol;
    private final Map<Long, Double> stockInfo;

    public Stock(String stockSymbol, Map<Long, Double> stockInfo) {
        this.stockSymbol = stockSymbol;
        this.stockInfo = stockInfo;
    }

    public Map<Long, Double> getStockInfo() {
        return stockInfo;
    }
}
