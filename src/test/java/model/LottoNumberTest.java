package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoNumberTest {

    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;

    @ParameterizedTest
    @DisplayName("입력 받은 값이 1 ~ 45 내에 있는 숫자가 아니면 예외처리를 반환한다.")
    @ValueSource(ints = {0, 46})
    void create(final int number) {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.valueOf(number))
                .withMessage(String.format("로또 넘버를 생성 할 수 없습니다. 로또 넘버는 %d ~ %d 사이의 숫자여야 합니다.", MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE));
    }

    @Test
    @DisplayName("숫자를 반환한다.")
    void getNumber() {
        //given
        LottoNumber number = LottoNumber.valueOf(1);
        int expect = 1;

        //when
        int actual = number.getValue();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}