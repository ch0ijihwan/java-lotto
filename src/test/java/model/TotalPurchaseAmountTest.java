package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class TotalPurchaseAmountTest {

    @Test
    @DisplayName("구매 금액이 0 보다 크지 않은 경우 예외처리 반환한다.")
    void validateRange() {
        //given
        int totalPurchaseAmountInput = 0;
        int lottoPrice = 1000;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new TotalPurchaseAmount(totalPurchaseAmountInput, lottoPrice))
                .withMessage("로또를 구매하려면 0 초과의 금액을 입력해야합니다.");
    }

    @Test
    @DisplayName("입력 받은 구매 금액이 로또의 한장 가격의 배수가 아닌경우 예외처리를 반환한다.")
    void validateMultipleOfLottoPrice() {
        //given
        int totalPurchaseAmountInput = 1001;
        int lottoPrice = 1000;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new TotalPurchaseAmount(totalPurchaseAmountInput, lottoPrice))
                .withMessage("로또 가격의 배수인 금액을 넣어야 로또를 구매 할 수 있습니다.");
    }

    @Test
    @DisplayName("남은 총 구매 가능 횟수를 반환한다.")
    void getCountOfTotalPurchase() {
        //given
        int totalPurchaseAmountInput = 5000;
        int lottoPrice = 1000;
        TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmountInput, lottoPrice);
        int expect = 5;

        //when
        int actual = totalPurchaseAmount.getCountOfTotalPurchase();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("총 투입 금액 반환")
    void getValue() {
        //given
        int totalPurchaseAmountInput = 5000;
        int lottoPrice = 1000;
        TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmountInput, lottoPrice);
        int expect = 5000;

        //when
        int actual = totalPurchaseAmount.getValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}