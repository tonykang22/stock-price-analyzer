package hello.stockpriceanalyzer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryStockRepository implements StockRepository {

    private final Map<String, Stock> store;

    public MemoryStockRepository() {
        store = new HashMap<>();
    }

    @Override
    public void saveStock(Stock stock) {
        store.put(stock.getStockSymbol(), stock);
    }

    @Override
    public Optional<Stock> findByStockSymbol(String symbol) {
        return Optional.ofNullable(store.get(symbol));
    }

}
