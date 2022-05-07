package model;

import model.vo.Lotto;
import model.vo.LottoNumber;
import model.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static model.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.params.provider.Arguments.of;

class LottosTest {

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

    @ParameterizedTest
    @DisplayName("lottos 가 순서 상관없이 모두 같은 lotto 들을 갖고 있으면 같은 객체로 판단한다.")
    @MethodSource("createLottosParameterProvider")
    void equals(final Lottos otherLottos, final boolean expect) {
        //given
        Lottos lottos = new Lottos(List.of(
                Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12))
        ));

        //when
        boolean actual = lottos.equals(otherLottos);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createLottosParameterProvider() {
        return Stream.of(
                of(new Lottos(List.of(
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)),
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)))), true),
                of(new Lottos(List.of(
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)))), true),
                of(new Lottos(List.of(
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 45)),
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                        Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)))), false));
    }
}
