package model.lotto;

import model.lotto.factory.LottoFactory;
import model.lotto.vo.LastWinningLotto;
import model.lotto.vo.LotteryTicket;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.result.vo.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.result.vo.Rank.*;
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

    @Test
    @DisplayName("새로운 로또를 입력 받아 새로운 로또 티켓을 만들어 반환한다.")
    void addLotto() {
        //given
        List<Lotto> lottos = List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(7, 8, 9, 10, 11, 12))
        );
        Lotto input = LottoFactory.createManualLotto(List.of(11, 12, 13, 14, 15, 16));
        LotteryTicket expect = new LotteryTicket(List.of(
                LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)),
                LottoFactory.createManualLotto(List.of(7, 8, 9, 10, 11, 12)),
                LottoFactory.createManualLotto(List.of(11, 12, 13, 14, 15, 16))
        ));
        LotteryTicket lotteryTicket = new LotteryTicket(lottos);
        //when
        LotteryTicket actual = lotteryTicket.addLotto(input);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}