package hello.stockpriceanalyzer;

import java.util.Optional;

public interface StockRepository {

    void saveStock(Stock stock);

    Optional<Stock> findByStockSymbol(String symbol);
}
