package model;

import model.vo.LottoNumbers;
import model.vo.LottoNumber;
import model.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class WinningLottoNumbersTest {

    @Test
    @DisplayName("보너스 넘버와 당첨 로또 넘버 중 중복이 있으면 예외처리 반환")
    void validateDuplication() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoNumbers lottoNumbers = LottoNumbers.createManualLottoNumbers(numbers);
        LottoNumber bonusBallNumber = LottoNumber.valueOf(6);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new WinningLotto(lottoNumbers, bonusBallNumber))
                .withMessage("보너스 넘버와 당첨 넘버 중 중복이 있습니다.");
    }

    @ParameterizedTest
    @DisplayName("입력 받은 로또와 당첨 로또를 비교하여 Rank 를 반환한다.")
    @MethodSource("createMatchingLottoParameterProvider")
    void measureRank(final LottoNumbers comparisonLottoNumbers, final Rank expect) {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoNumbers lottoNumbers = LottoNumbers.createManualLottoNumbers(numbers);
        LottoNumber bonusBallNumber = LottoNumber.valueOf(7);

        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusBallNumber);

        //when
        Rank actual = winningLotto.measureRank(comparisonLottoNumbers);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createMatchingLottoParameterProvider() {
        return Stream.of(
                Arguments.of(
                        LottoNumbers.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST,
                        LottoNumbers.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND,
                        LottoNumbers.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 43)), Rank.THIRD)
        );
    }
}