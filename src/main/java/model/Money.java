package model;

public class Money {

    private static final int ONE_LOTTO_PRICE = 1000;
    private final int inputtedMoney;
    private static final int MINIMUM_VALUE_OF_MONEY = 0;

    public Money(final int inputtedMoney) {
        validateRangeOfMoney(inputtedMoney);
        this.inputtedMoney = inputtedMoney;
    }

    private void validateRangeOfMoney(final int inputtedMoney) {
        if (inputtedMoney < MINIMUM_VALUE_OF_MONEY) {
            throw new IllegalArgumentException(String.format("%d 보다 커야합니다.", MINIMUM_VALUE_OF_MONEY));
        }
    }

    public int getChangeToBuyLotto() {
        return inputtedMoney / ONE_LOTTO_PRICE;
    }

    public int getMoney() {
        return inputtedMoney;
    }

    public double calculateRateOfProfit(final Money totalMoney) {
        return (double) totalMoney.getMoney() / getMoney();
    }
}
