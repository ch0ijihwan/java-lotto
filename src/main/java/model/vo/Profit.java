package model.vo;

import java.util.Map;
import java.util.Objects;

public class Profit {

    private final double profit;
    private final Money initialMoney;

    public Profit(final Money initialMoney, final Map<Rank, Integer> lottoGameResult) {
        profit = calculateProfit(lottoGameResult);
        this.initialMoney = initialMoney;
    }

    private double calculateProfit(final Map<Rank, Integer> resultTotalRanks) {
        return resultTotalRanks.keySet()
                .stream()
                .mapToInt(targetRank -> targetRank.getDividend() * resultTotalRanks.get(targetRank))
                .sum();
    }

    public double getRateOfProfit() {
        return profit / initialMoney.getMoneyValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit1 = (Profit) o;
        return Double.compare(profit1.profit, profit) == 0 && Objects.equals(initialMoney, profit1.initialMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profit, initialMoney);
    }
}
