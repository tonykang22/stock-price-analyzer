package hello.stockpriceanalyzer.repository;

import hello.stockpriceanalyzer.dto.StockDto;

import java.util.Optional;

public interface StockRepository {

    void saveStock(StockDto stock);

    Optional<StockDto> findByStockSymbol(String symbol);
}
