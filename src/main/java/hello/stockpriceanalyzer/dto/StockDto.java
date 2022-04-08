package hello.stockpriceanalyzer.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class StockDto {

    private final String stockSymbol;
    private final Map<Long, Double> stockInfo;

    public StockDto(String stockSymbol, Map<Long, Double> stockInfo) {
        this.stockSymbol = stockSymbol;
        this.stockInfo = stockInfo;
    }

}
