package model.lotto;

import model.lotto.factory.LottoFactory;
import model.lotto.vo.LastWinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.result.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LastWinningLottoTest {

    @Test
    @DisplayName("보너스 넘버와 당첨 로또 넘버는 중복 될 수 없습니다.")
    void validateDuplicationWithBonusNumber() {
        //given
        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(6);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new LastWinningLotto(winningLottoInput, bonusNumber))
                .withMessage("보너스 넘버와 당첨 로또 넘버는 중복 될 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("로또 넘버를 받아, 당첨 로또와 비교하여 해당 등수를 반환한다.")
    @MethodSource("createMatchingLottoParameterProvider")
    void judgeRank(final Lotto lottoToCompare, final Rank expect) {
        //given
        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        LastWinningLotto lastWinningLotto = new LastWinningLotto(winningLottoInput, bonusNumber);

        //when
        Rank actual = lastWinningLotto.judgeRank(lottoToCompare);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createMatchingLottoParameterProvider() {
        return Stream.of(
                Arguments.of(
                        LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST,
                        LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 7)), Rank.SECOND,
                        LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 45)), Rank.THIRD,
                        LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 44, 45)), Rank.FOURTH)
        );
    }
}