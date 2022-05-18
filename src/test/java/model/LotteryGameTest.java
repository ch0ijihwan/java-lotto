package model;

import model.lotto.entity.LotteryGame;
import model.lotto.factory.LottoFactory;
import model.lotto.vo.LastWinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.result.vo.LotteryResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static model.result.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LotteryGameTest {

    @Test
    @DisplayName("생성자로 부터 입력 받은 자동 로또 횟수 만큼 자동 로또 리스트를 생성한다..")
    void getInformationOfLottos() {
        //given
        int totalPurchaseAmount = 10000;
        int expectSize = 10;
        //when
        LotteryGame actual = new LotteryGame(totalPurchaseAmount, 0);

        //then
        assertThat(actual.getInformationOfLottos()).hasSize(expectSize);
    }

    @Test
    @DisplayName("수동 로또 구매 횟수를 반환한다.")
    void getCountOfManualPurchase() {
        //given
        int totalPurchaseAmount = 10000;
        int input = 5;
        int expect = 5;
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, input);

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
        int countOManualLottos = 5;
        int expect = 5;
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, countOManualLottos);

        //when
        int actual = lotteryGame.getCountOfAutoPurchase();

        //then
        assertThat(actual).isEqualTo(expect);
    }


    @Test
    @DisplayName("로또 최종 결과를 Map<Rank,Integer> 타입으로 반환한다.")
    void getTotalResultOfLotto() {
        //given
        int totalPurchaseAmount = 10000;
        LotteryGame lotteryGame = new LotteryGame(totalPurchaseAmount, 5);
        lotteryGame.addManualLotto(LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6)));
        lotteryGame.addManualLotto(LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 7)));
        lotteryGame.addManualLotto(LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 45)));
        lotteryGame.addManualLotto(LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 44, 45)));
        lotteryGame.addManualLotto(LottoFactory.createManualLotto(List.of(1, 2, 3, 45, 44, 43)));

        Lotto winningLottoInput = LottoFactory.createManualLotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = LottoNumber.valueOf(7);

        LastWinningLotto lastWinningLotto = new LastWinningLotto(winningLottoInput, bonusNumber);
        int expectFrequencyOfRank = 1;

        //when
        LotteryResult actual = lotteryGame.getLotteryResult(lastWinningLotto);

        //then
        assertAll(
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(FIRST, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(SECOND, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(THIRD, expectFrequencyOfRank),
                () -> assertThat(actual.getRankAndFrequencyNumber()).containsEntry(FOURTH, expectFrequencyOfRank)
        );
    }
}