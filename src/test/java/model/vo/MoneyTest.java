package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoneyTest {

    private static final int MINIMUM_VALUE_OF_MONEY = 0;

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
    @DisplayName("로도 금액을 입력 받아, 총 몇장 구매할 수 있는지 반환한다.")
    void getChanceToBuyLotto() {
        //given
        Money money = new Money(10000);
        int lottoPrice = 1000;
        int expect = 10;

        //when
        int actual = money.getChanceToBuyLotto(lottoPrice);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("입력 받은 로또 금액으로 돈을 나눌 경우, 나머지가 없으면 true 를 반환한다")
    @MethodSource("createLottoPriceParameterProvider")
    void isPerfectlyDivisible(final int oneLottoPrice, final boolean expect) {
        //given
        Money money = new Money(10000);

        //when
        boolean actual = money.isPerfectlyDivisible(oneLottoPrice);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createLottoPriceParameterProvider() {
        return Stream.of(
                Arguments.of(1000, true), Arguments.of(1001, false)
        );
    }
}