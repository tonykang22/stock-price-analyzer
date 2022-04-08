package hello.stockpriceanalyzer;

import java.util.Map;

public class MemoryStockRepository implements StockRepository {

    private Map<String, Stock> store;

    @Override
    public void saveStock(Stock stock) {
        store.put(stock.getStockSymbol(), stock);
    }

    @Override
    public Stock findByStockSymbol(String symbol) {
        return store.get(symbol);
    }
}
