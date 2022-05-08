package model.lottoinformationinputter;

import model.vo.Money;

public class LottoVendingMachine {

    private static final int ONE_LOTTO_PRICE = 1000;

    private final Money inputtedMoney;
    private final int countOfManualLotto;

    public LottoVendingMachine(final Money money, final int countOfManualLotto) {
        validateInputtedMoneyByLottoPrice(money);
        this.inputtedMoney = money;
        this.countOfManualLotto = countOfManualLotto;
    }

    private void validateInputtedMoneyByLottoPrice(final Money money) {
        if (!money.isPerfectlyDivisible(ONE_LOTTO_PRICE)) {
            throw new IllegalArgumentException("입력 금액은 1000원의 배수 단위어야 로또를 구매할 수 있습니다.");
        }
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return inputtedMoney.getChanceToBuyLotto(ONE_LOTTO_PRICE) - countOfManualLotto;
    }
}
