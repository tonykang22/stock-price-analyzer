package hello.stockpriceanalyzer.repository;

import hello.stockpriceanalyzer.domain.Stock;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryStockRepository implements StockRepository {

    private final Map<String, Stock> store;

    public MemoryStockRepository() {
        store = new HashMap<>();
    }

    @Override
    public Stock saveStock(Stock stock) {
        store.put(stock.getStockSymbol(), stock);
        return stock;
    }

    @Override
    public Optional<Stock> findByStockSymbol(String symbol) {
        return Optional.ofNullable(store.get(symbol));
    }

}
