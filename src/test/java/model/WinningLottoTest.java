package model;

import model.lotto.Lotto;
import model.lotto.WinningLotto;
import model.vo.LottoNumber;
import model.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    @ParameterizedTest
    @DisplayName("입력 받은 로또와 당첨 로또를 비교하여 Rank 를 반환한다.")
    @MethodSource("createMatchingLottoParameterProvider")
    void measureRank(final Lotto comparisonLotto, final Rank expect) {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.createManualLottoNumbers(numbers);
        LottoNumber bonusBallNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(lotto, bonusBallNumber);

        //when
        Rank actual = winningLotto.measureRank(comparisonLotto);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createMatchingLottoParameterProvider() {
        return Stream.of(
                Arguments.of(
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST,
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND,
                        Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 43)), Rank.THIRD)
        );
    }
}