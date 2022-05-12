package model;

import java.util.Objects;

public class CountOfManualPurchase {

    private static final int MIN_COUNT_OF_MANUAL_PURCHASE = 0;

    private final int value;

    public CountOfManualPurchase(final int countOfManualPurchaseInput, final int countOfTotalPurchaseInput) {
        validateByTotalPurchaseAmount(countOfManualPurchaseInput, countOfTotalPurchaseInput);
        validateRange(countOfManualPurchaseInput);
        this.value = countOfManualPurchaseInput;
    }

    private void validateByTotalPurchaseAmount(final int countOfManualPurchaseInput, final int countOfTotalPurchaseInput) {
        if (countOfManualPurchaseInput > countOfTotalPurchaseInput) {
            throw new IllegalArgumentException("수동 구매 횟수가 전체 구매 가능 횟수보다 큽니다.");
        }
    }

    private void validateRange(final int countOfManualPurchaseInput) {
        if (countOfManualPurchaseInput < MIN_COUNT_OF_MANUAL_PURCHASE) {
            throw new IllegalArgumentException("수동 구매 횟수는 음수가 아닌 정수이어야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountOfManualPurchase that = (CountOfManualPurchase) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
