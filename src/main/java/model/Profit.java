package model;

import model.vo.Money;
import model.vo.Rank;

import java.util.Map;

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
}
