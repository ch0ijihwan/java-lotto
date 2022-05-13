package model.lotto;

import model.factory.LottoFactory;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.lotto.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.lotto.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottosTest {

    @Test
    @DisplayName("로또들을 반환한다.")
    void getLottos() {
        //given
        List<Lotto> input = List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(7, 8, 9, 10, 11, 12))
        );
        Lottos lottos = new Lottos(input);

        //when
        List<Lotto> actual = lottos.getLottos();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @Test
    @DisplayName("당첨 로또를 받아, 로또 당첨 결과 리스트를 반환한다.")
    void getMatchingResult() {
        //given
        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoInput, bonusNumber);

        List<Lotto> input = List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 7)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 45)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 45, 44, 43))
        );
        Lottos lottos = new Lottos(input);
        List<Rank> expect = List.of(FIRST, SECOND, THIRD, FIFTH);

        //when
        List<Rank> actual = lottos.getMatchingResult(winningLotto);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}