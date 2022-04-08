package hello.stockpriceanalyzer;

import lombok.Getter;

@Getter
public class MaxProfit {

    private final long purchaseDate;
    private final long sellDate;
    private final double profit;
    private final double priceVariance;

    public MaxProfit(long purchaseDate, long sellDate, double profit, double priceVariance) {
        this.purchaseDate = purchaseDate;
        this.sellDate = sellDate;
        this.profit = profit;
        this.priceVariance = priceVariance;
    }
}
