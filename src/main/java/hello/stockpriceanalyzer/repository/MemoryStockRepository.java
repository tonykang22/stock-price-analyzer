package hello.stockpriceanalyzer.repository;

import hello.stockpriceanalyzer.dto.StockDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryStockRepository implements StockRepository {

    private final Map<String, StockDto> store;

    public MemoryStockRepository() {
        store = new HashMap<>();
    }

    @Override
    public void saveStock(StockDto stock) {
        store.put(stock.getStockSymbol(), stock);
    }

    @Override
    public Optional<StockDto> findByStockSymbol(String symbol) {
        return Optional.ofNullable(store.get(symbol));
    }

}
