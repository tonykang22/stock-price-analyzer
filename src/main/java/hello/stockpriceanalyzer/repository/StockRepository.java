package hello.stockpriceanalyzer.repository;

import hello.stockpriceanalyzer.domain.Stock;

import java.util.Optional;

public interface StockRepository {

    Stock saveStock(Stock stock);

    Optional<Stock> findByStockSymbol(String symbol);
}
