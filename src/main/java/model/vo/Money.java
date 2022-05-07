package model.vo;

import java.util.Objects;

public class Money {

    private static final int MINIMUM_VALUE_OF_MONEY = 0;

    private final int moneyValue;

    public Money(final int moneyValue) {
        validate(moneyValue);
        this.moneyValue = moneyValue;
    }

    private void validate(final int moneyValue) {
        validateRangeOfMoney(moneyValue);
    }

    private void validateRangeOfMoney(final int inputtedMoney) {
        if (inputtedMoney < MINIMUM_VALUE_OF_MONEY) {
            throw new IllegalArgumentException(String.format("%d 보다 커야합니다.", MINIMUM_VALUE_OF_MONEY));
        }
    }

    public int getChanceToBuyLotto(final int lottoPrice) {
        return moneyValue / lottoPrice;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public boolean isPerfectlyDivisible(final int oneLottoPrice) {
        return moneyValue % oneLottoPrice == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return moneyValue == money.moneyValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(moneyValue);
    }
}
