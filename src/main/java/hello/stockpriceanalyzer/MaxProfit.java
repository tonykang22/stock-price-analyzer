package hello.stockpriceanalyzer;

import lombok.Getter;

@Getter
public class MaxProfit {

    private final String purchaseDate;
    private final String sellDate;
    private final double profit;
    private final double percentage;

    public MaxProfit(String purchaseDate, String sellDate, double profit, double percentage) {
        this.purchaseDate = purchaseDate;
        this.sellDate = sellDate;
        this.profit = profit;
        this.percentage = percentage;
    }
}
