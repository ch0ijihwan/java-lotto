package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CountOfManualPurchaseTest {

    @Test
    @DisplayName("수동 구매 횟수는 0보다 크지 않은 경우 예외처리를 반환한다.")
    void validateRange() {
        //given
        int countOfManualPurchaseInput = -1;
        int countOfTotalPurchaseInput = 3;


        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfManualPurchase(countOfManualPurchaseInput, countOfTotalPurchaseInput))
                .withMessage("수동 구매 횟수는 음수가 아닌 정수이어야 합니다.");
    }

    @Test
    @DisplayName("전체 구매 양의 구매 가능 횟수 보다 수동 구매 횟수가 큰 경우 예외처리 반환.")
    void validateByTotalPurchaseAmount() {
        //given
        int countOfManualPurchaseInput = 10;
        int countOfTotalPurchaseInput = 9;

        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CountOfManualPurchase(countOfManualPurchaseInput, countOfTotalPurchaseInput))
                .withMessage("수동 구매 횟수가 전체 구매 가능 횟수보다 큽니다.");
    }

    @Test
    @DisplayName("값을 반환한다.")
    void getValue() {
        //given
        int countOfManualPurchaseInput = 1;
        int countOfTotalPurchaseInput = 9;
        int expect = 1;
        CountOfManualPurchase countOfManualPurchase
                = new CountOfManualPurchase(countOfManualPurchaseInput, countOfTotalPurchaseInput);

        //when
        int actual = countOfManualPurchase.getValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}
