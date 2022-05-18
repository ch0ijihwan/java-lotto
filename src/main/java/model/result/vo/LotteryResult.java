package model.result.vo;

import model.purchase.vo.TotalPurchaseAmount;

import java.util.Map;
import java.util.Objects;

public class LotteryResult {

    private final Map<Rank, Integer> rankAndFrequencyNumber;
    private final TotalPurchaseAmount totalPurchaseAmount;

    public LotteryResult(final Map<Rank, Integer> rankAndFrequency, final TotalPurchaseAmount totalPurchaseAmount) {
        this.rankAndFrequencyNumber = rankAndFrequency;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Map<Rank, Integer> getRankAndFrequencyNumber() {
        return rankAndFrequencyNumber;
    }


    public double calculateRateOfProfit() {
        int winningAmount = rankAndFrequencyNumber.keySet()
                .stream()
                .mapToInt(this::sumUpRewardOfRank)
                .sum();

        return (double) winningAmount / totalPurchaseAmount.getTotalPurchaseAmount();
    }

    private int sumUpRewardOfRank(final Rank rank) {
        return rank.getReward() * rankAndFrequencyNumber.get(rank);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryResult that = (LotteryResult) o;
        return Objects.equals(rankAndFrequencyNumber, that.rankAndFrequencyNumber) && Objects.equals(totalPurchaseAmount, that.totalPurchaseAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankAndFrequencyNumber, totalPurchaseAmount);
    }
}