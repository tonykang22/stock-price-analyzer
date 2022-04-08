package hello.stockpriceanalyzer.service;

import hello.stockpriceanalyzer.dto.MaxProfitDto;
import hello.stockpriceanalyzer.dto.StockDto;
import hello.stockpriceanalyzer.repository.MemoryStockRepository;
import hello.stockpriceanalyzer.repository.StockRepository;
import hello.stockpriceanalyzer.service.thirdparty.StockSourceAPI;
import hello.stockpriceanalyzer.service.thirdparty.YahooFinance;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository = new MemoryStockRepository();
    private final StockSourceAPI sourceAPI = new YahooFinance();

    public StockDto findByStockSymbol(String symbol) throws IOException, ParseException {
        Optional<StockDto> optional = stockRepository.findByStockSymbol(symbol);

        if (optional.isPresent()) {
            return optional.get();
        }

        StockDto stock = loadDataFromThirdParty(symbol);
        stockRepository.saveStock(stock);
        return stock;
    }

    @Override
    public MaxProfitDto calculateProfit(StockDto stock) throws IOException, ParseException {
        Map<Long, Double> stockInfo = stock.getStockInfo();
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

        MaxProfitDto maxProfit = converToMaxProfit(purchaseDate, sellDate, profit, min);

        return maxProfit;
    }

    private MaxProfitDto converToMaxProfit(long purchaseDate, long sellDate, double profit, double min) {
        String convertedPurchaseDate = convertUnixTime(purchaseDate);
        String convertedSellDate = convertUnixTime(sellDate);
        double convertedProfit = Double.parseDouble(String.format("%.2f", profit));
        double convertedPercentage = Double.parseDouble(String.format("%.2f", (profit / min) * 100));
        return new MaxProfitDto(convertedPurchaseDate, convertedSellDate, convertedProfit, convertedPercentage);
    }

    private String convertUnixTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
        date.setTime(timestamp * 1000L);
        return sdf.format(date);
    }

    private StockDto loadDataFromThirdParty(String symbol) throws IOException, ParseException {
        StockDto stock = sourceAPI.provideStockInformation(symbol);
        return sourceAPI.provideStockInformation(symbol);
    }

}
