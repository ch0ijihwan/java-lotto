package model.lotto.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("로또 넘버는 1에서 45 까지어야 합니다.")
    @ValueSource(ints = {0, 46})
    void validateRange(final int input) {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> LottoNumber.valueOf(input))
                .withMessage("로또 넘버는 1에서 45 까지어야 합니다.");
    }

    @Test
    @DisplayName("로또 넘버 값을 반환한다.")
    void getValue() {
        //given
        int input = 1;
        LottoNumber lottoNumber = LottoNumber.valueOf(input);

        //when
        int actual = lottoNumber.getValue();

        //then
        assertThat(actual).isEqualTo(input);
    }
}