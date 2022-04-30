package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottosTest {

    @Test
    @DisplayName("입력 받은 로또 구매 횟수에 따라, 로또를 생성한다.")
    void crate() {
        //given
        Money money = new Money(8000);
        int expectSize = 8;

        //when
        Lottos lottos = new Lottos(money);

        //then
        assertThat(lottos).extracting("lottos")
                .asList()
                .size()
                .isEqualTo(expectSize);
    }

    @Test
    @DisplayName("로또의 당첨 등수들을 가진 Map 을 반환한다.")
    void measureLottoRanks() {
        //given
        Lottos lottos = new Lottos(List.of(
                Lotto.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createLottoNumbers(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.createLottoNumbers(List.of(1, 2, 3, 4, 5, 10)),
                Lotto.createLottoNumbers(List.of(7, 8, 9, 10, 11, 12))
        ));

        WinningLotto winningLotto = new WinningLotto(Lotto.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7));

        //when
        Map<Rank, Integer> lottoResults = lottos.countLottoRanks(winningLotto);

        //then
        assertAll(
                () -> assertThat(lottoResults.containsKey(Rank.FIFTH)).isTrue(),
                () -> assertThat(lottoResults.containsKey(Rank.SECOND)).isTrue(),
                () -> assertThat(lottoResults.containsKey(Rank.THIRD)).isTrue(),
                () -> assertThat(lottoResults.containsKey(Rank.LAST)).isTrue()
        );
    }
}