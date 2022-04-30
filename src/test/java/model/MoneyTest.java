package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoneyTest {

    private static final int MINIMUM_VALUE_OF_MONEY = 0;
    private Money money;

    @BeforeEach
    void setUp() {
        money = new Money(10000);
    }

    @Test
    @DisplayName("입력 금액은 자연수가 아닌 경우 예외처리 반환")
    void validateRangeOfMoney() {
        //given
        int input = -1;
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Money(input))
                .withMessage(String.format("%d 보다 커야합니다.", MINIMUM_VALUE_OF_MONEY));
    }

    @Test
    @DisplayName("로또를 구매할 수 있는 횟수를 반환한다.")
    void getChangeToBuyLotto() {
        //given
        int expect = 10;

        //when
        int actual = money.getChanceToBuyLotto();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("현재 금액을 반환한다.")
    void getMoney() {
        //given
        int expect = 10000;

        //when
        int actual = money.getMoney();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("이익률을 반환한다.")
    void calculateRateOfProfit() {
        //given
        Money totalProfit = new Money(15000);
        double expect = 1.5;

        //when
        double actual = money.calculateRateOfProfit(totalProfit);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}