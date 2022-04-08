package hello.stockpriceanalyzer;

import lombok.Getter;

@Getter
public class MaxProfit {

    private final long purchaseDate;
    private final long sellDate;
    private final double profit;
    private final double percentage;

    public MaxProfit(long purchaseDate, long sellDate, double profit, double percentage) {
        this.purchaseDate = purchaseDate;
        this.sellDate = sellDate;
        this.profit = profit;
        this.percentage = percentage;
    }
}
