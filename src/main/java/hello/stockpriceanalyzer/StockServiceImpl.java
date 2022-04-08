package hello.stockpriceanalyzer;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        double min = prices.get(0);
        long purchaseDate = 0l;
        long sellDate = 0l;

        for (int i = 0; i < prices.size(); ++i) {
            if (min < prices.get(i)) {
                if (profit < prices.get(i) - min) {
                    profit = prices.get(i) - min;
                    sellDate = dates.get(i);
                    continue;
                }
                continue;
            }
            if (purchaseDate < sellDate) {
                continue;
            }
            purchaseDate = dates.get(i);
            min = prices.get(i);
        }

        MaxProfit maxProfit = converToMaxProfit(purchaseDate, sellDate, profit, min);

        return maxProfit;
    }

    private MaxProfit converToMaxProfit(long purchaseDate, long sellDate, double profit, double min) {
        String convertedPurchaseDate = convertUnixTime(purchaseDate);
        String convertedSellDate = convertUnixTime(sellDate);
        double convertedProfit = Double.parseDouble(String.format("%.2f", profit));
        double convertedPercentage = Double.parseDouble(String.format("%.2f", (profit / min) * 100));
        return new MaxProfit(convertedPurchaseDate, convertedSellDate, convertedProfit, convertedPercentage);
    }

    private String convertUnixTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        date.setTime(timestamp * 1000L);
        return sdf.format(date);
    }

    private Stock findByStockSymbol(String symbol) throws IOException, ParseException {
        Optional<Stock> optional = stockRepository.findByStockSymbol(symbol);

        if (optional.isPresent()) {
            return optional.get();
        }

        Stock stock = loadDataFromThirdParty(symbol);
        stockRepository.saveStock(stock);
        return stock;
    }

    private Stock loadDataFromThirdParty(String symbol) throws IOException, ParseException {
        Stock stock = sourceAPI.provideStockInformation(symbol);
        return sourceAPI.provideStockInformation(symbol);
    }

}
