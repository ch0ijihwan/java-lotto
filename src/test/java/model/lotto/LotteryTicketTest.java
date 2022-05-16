package model.lotto;

import model.lotto.factory.LottoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.lotto.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class LotteryTicketTest {

    @Test
    @DisplayName("로또들을 반환한다.")
    void getLotteryTicket() {
        //given
        List<Lotto> input = List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(7, 8, 9, 10, 11, 12))
        );
        LotteryTicket lotteryTicket = new LotteryTicket(input);

        //when
        List<Lotto> actual = lotteryTicket.getLotteryTicket();

        //then
        assertThat(actual).isEqualTo(input);
    }

    @Test
    @DisplayName("당첨 로또를 받아, 로또 당첨 결과 리스트를 반환한다.")
    void getMatchingResult() {
        //given
        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        LastWinningLotto lastWinningLotto = new LastWinningLotto(winningLottoInput, bonusNumber);

        List<Lotto> input = List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 7)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 45)),
                LottoFactory.createManualLotto(List.of(1, 2, 3, 45, 44, 43))
        );
        LotteryTicket lotteryTicket = new LotteryTicket(input);
        List<Rank> expect = List.of(FIRST, SECOND, THIRD, FIFTH);

        //when
        List<Rank> actual = lotteryTicket.getMatchingResult(lastWinningLotto);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}