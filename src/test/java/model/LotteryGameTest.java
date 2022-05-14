package model;

import model.dto.LottoDto;
import model.factory.LottoFactory;
import model.lotto.WinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.lotto.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LotteryGameTest {

    @Test
    @DisplayName("생성자로 부터 입력 받은 자동 로또 횟수로 만든 자동 로또와, 입력받은 수동 로또로 만든 lottos를 반환한다.")
    void getInformationOfLottos() {
        //given
        int totalPurchaseAmount = 10000;
        List<LottoDto> manualLottosInput = List.of(
                new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoDto(List.of(11, 12, 13, 14, 15, 16))
        );
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, manualLottosInput);
        int expectSize = 10;
        //when
        List<LottoDto> actual = lotteryGame.getInformationOfLottos();

        //then
        assertThat(actual.size()).isEqualTo(expectSize);
    }

    @Test
    @DisplayName("수동 로또 구매 횟수를 반환한다.")
    void getCountOfManualPurchase() {
        //given
        int totalPurchaseAmount = 10000;
        List<LottoDto> manualLottosInput = List.of(
                new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoDto(List.of(11, 12, 13, 14, 15, 16))
        );
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, manualLottosInput);
        int expect = 2;
        //when
        int actual = lotteryGame.getCountOfManualPurchase();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("자동 로또 구매 횟수를 반환한다.")
    void getCountOfAutoPurchase() {
        //given
        int totalPurchaseAmount = 10000;
        List<LottoDto> manualLottosInput = List.of(
                new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoDto(List.of(11, 12, 13, 14, 15, 16))
        );
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, manualLottosInput);
        int expect = 8;

        //when
        int actual = lotteryGame.getCountOfAutoPurchase();

        //then
        assertThat(actual).isEqualTo(expect);
    }


    @Test
    @DisplayName("로또 최종 결과를 Map<Rank,Integer> 타입으로 반환한다.")
    void getTotalResultOfLotto() {
        //given
        int totalPurchaseAmount = 5000;
        List<LottoDto> manualLottosInput = List.of(
                new LottoDto(List.of(1, 2, 3, 4, 5, 6)),
                new LottoDto(List.of(1, 2, 3, 4, 5, 7)),
                new LottoDto(List.of(1, 2, 3, 4, 5, 45)),
                new LottoDto(List.of(1, 2, 3, 4, 44, 45)),
                new LottoDto(List.of(1, 2, 3, 45, 44, 43))
        );
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, manualLottosInput);

        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoInput, bonusNumber);

        int expectFrequencyOfRank = 1;

        //when
        LotteryResult actual = lotteryGame.getLotteryResult(winningLotto);

        //then
        assertAll(
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(FIRST, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(SECOND, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(THIRD, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(FOURTH, expectFrequencyOfRank)
        );
    }
}