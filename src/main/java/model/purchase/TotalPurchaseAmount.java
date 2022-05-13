package model.purchase;

public class TotalPurchaseAmount {

    private static final int MIN_TOTAL_PURCHASE_AMOUNT = 0;

    private final int totalPurchaseAmount;
    private final int lottoPrice;

    public TotalPurchaseAmount(final int totalPurchaseAmount, final int lottoPrice) {
        validateRange(totalPurchaseAmount);
        validateMultipleOfLottoPrice(totalPurchaseAmount, lottoPrice);
        this.totalPurchaseAmount = totalPurchaseAmount;
        this.lottoPrice = lottoPrice;
    }

    private void validateRange(final int totalPurchaseAmount) {
        if (totalPurchaseAmount <= MIN_TOTAL_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException("로또를 구매하려면 0 초과의 금액을 입력해야합니다.");
        }
    }

    private void validateMultipleOfLottoPrice(final int totalPurchaseAmount, final int lottoPrice) {
        if (totalPurchaseAmount % lottoPrice != 0) {
            throw new IllegalArgumentException("로또 가격의 배수인 금액을 넣어야 로또를 구매 할 수 있습니다.");
        }
    }

    public int getCountOfTotalPurchase() {
        return totalPurchaseAmount / lottoPrice;
    }

    public int getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }
}
