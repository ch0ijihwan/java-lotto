package model;

import model.vo.Lotto;
import model.vo.LottoNumber;
import model.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static model.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottosTest {

    @Test
    @DisplayName("입력 받은 로또 구매 횟수에 따라, 로또를 생성한다.")
    void crate() {
        //given
        int input = 8;
        int expectSize = 8;

        //when
        Lottos lottos = new Lottos(input);

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
                Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 7)),
                Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 10)),
                Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12))
        ));
        int expectFrequencyOfRank = 1;

        WinningLotto winningLotto = new WinningLotto(Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7));

        //when
        Map<Rank, Integer> lottoResults = lottos.countLottoRanks(winningLotto);

        //then
        assertAll(
                () -> assertThat(lottoResults).containsEntry(FIRST, expectFrequencyOfRank),
                () -> assertThat(lottoResults).containsEntry(SECOND, expectFrequencyOfRank),
                () -> assertThat(lottoResults).containsEntry(THIRD, expectFrequencyOfRank),
                () -> assertThat(lottoResults).containsEntry(LAST, expectFrequencyOfRank)
        );
    }
}
