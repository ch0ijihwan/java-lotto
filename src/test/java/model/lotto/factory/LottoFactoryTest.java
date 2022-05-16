package model.lotto.factory;

import model.lotto.Lotto;
import model.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {

    @Test
    @DisplayName("수동으로 입력 받은 번호들로 로또를 생성한다.")
    void createManualLotto() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        Lotto expect = new Lotto(List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        ));

        //when
        Lotto actual = LottoFactory.createManualLotto(input);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("자동 로또를 생성한다.")
    void createAutoLotto() {
        //given
        int expectSize = 6;

        //when
        Lotto actual = LottoFactory.createAutoLotto();

        //then
        assertThat(actual.getLottoNumbers().size()).isEqualTo(expectSize);
    }
}