package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vo.LottoNumber;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoTest {

    @Test
    @DisplayName("로또넘버 리스트를 반환한다.")
    void getLotto() {
        //given
        List<LottoNumber> input = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );
        Lotto lotto = new Lotto(input);

        //when
        List<LottoNumber> actual = lotto.getLottoNumbers();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @Test
    @DisplayName("로또 넘버 중 중복이 있으면 예외처리를 반환한다.")
    void validateDuplication() {
        //given
        List<LottoNumber> input = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(1), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(input))
                .withMessage("로또안의 로또 넘버는 중복이 될 수 없습니다.");
    }

    @Test
    @DisplayName("로또안의 로또 넘버는 6가지어야 한다.")
    void validateLottoSize() {
        //given
        List<LottoNumber> input = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6),
                LottoNumber.valueOf(7)
        );

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new Lotto(input))
                .withMessage("로또 안의 로또 넘버는 6가지어야 합니다.");
    }
}
