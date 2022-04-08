package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StockServiceImpl implements StockService {

    private StockRepository stockRepository = new MemoryStockRepository();
    private StockSourceAPI sourceAPI = new YahooFinance();

    @Override
    public MaxProfit calculateProfit(String symbol) throws IOException, ParseException {
        Stock findStock = findByStockSymbol(symbol);
        Map<Long, Double> stockInfo = findStock.getStockInfo();

        List<Long> dates = new ArrayList<>(stockInfo.keySet());
        List<Double> prices = new ArrayList<>(stockInfo.values());

        double profit = 0d;
        double max = 0d;
        double min = Double.MAX_VALUE;
        long purchaseDate = 0l;
        long sellDate = 0l;

        for (int i = 0; i < prices.size(); ++i) {
            if (min < prices.get(i)) {
                if (profit < prices.get(i) - min) {
                    max = prices.get(i);
                    profit = prices.get(i) - min;
                    sellDate = dates.get(i);
                }
                continue;
            }
            min = prices.get(i);
            purchaseDate = dates.get(i);
        }

        return new MaxProfit(purchaseDate, sellDate, profit, (profit / min) * 100);
    }

    private Stock findByStockSymbol(String symbol) throws IOException, ParseException {
        Optional<Stock> optional = stockRepository.findByStockSymbol(symbol);
        if (optional.isEmpty()) {
            Stock stock = loadDataFromThirdParty(symbol);
            stockRepository.saveStock(stock);
            return stock;
        }
        return optional.get();
    }

    private Stock loadDataFromThirdParty(String symbol) throws IOException, ParseException {
        Stock stock = sourceAPI.provideStockInformation(symbol);
        return sourceAPI.provideStockInformation(symbol);
    }

}
