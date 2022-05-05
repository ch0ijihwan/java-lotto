package model;

import model.vo.Rank;

import java.util.Map;

public class Money {

    private static final int MINIMUM_VALUE_OF_MONEY = 0;
    private static final int NO_REST = 0;
    private static final int ONE_LOTTO_PRICE = 1000;
    private final int moneyValue;

    public Money(final int moneyValue) {
        validate(moneyValue);
        this.moneyValue = moneyValue;
    }

    private void validate(final int moneyValue) {
        validateRangeOfMoney(moneyValue);
        validateAmountOfMoney(moneyValue);
    }

    private void validateRangeOfMoney(final int inputtedMoney) {
        if (inputtedMoney < MINIMUM_VALUE_OF_MONEY) {
            throw new IllegalArgumentException(String.format("%d 보다 커야합니다.", MINIMUM_VALUE_OF_MONEY));
        }
    }

    private void validateAmountOfMoney(final int inputtedMoney) {
        if (inputtedMoney % ONE_LOTTO_PRICE != NO_REST) {
            throw new IllegalArgumentException(String.format("입력 금액은 %d 의 배수여야 합니다.", ONE_LOTTO_PRICE));
        }
    }

    public int getChanceToBuyLotto() {
        return moneyValue / ONE_LOTTO_PRICE;
    }

    public double calculateRateOfProfit(final Map<Rank, Integer> resultTotalRanks) {
        double profit = resultTotalRanks.keySet()
                .stream()
                .mapToInt(targetRank -> targetRank.getDividend() * resultTotalRanks.get(targetRank))
                .sum();
        return profit / moneyValue;
    }
}
