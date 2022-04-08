package hello.stockpriceanalyzer;

public interface StockRepository {

    void saveStock(Stock stock);

    Stock findByStockSymbol(String symbol);
}
