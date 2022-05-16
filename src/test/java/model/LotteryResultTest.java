package model;

import model.lotto.Rank;
import model.purchase.TotalPurchaseAmount;
import model.result.LotteryResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LotteryResultTest {

    @Test
    @DisplayName("로또 결과를 반환한다.")
    void getLotteryResult() {
        //given
        TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount(5000, 1000);
        Map<Rank, Integer> inputLotteryResult = new HashMap<>();
        inputLotteryResult.put(Rank.FIRST, 1);
        inputLotteryResult.put(Rank.SECOND, 2);
        LotteryResult lotteryResult = new LotteryResult(inputLotteryResult, totalPurchaseAmount);

        //when
        Map<Rank, Integer> actual = lotteryResult.getRankAndFrequencyNumber();

        //then
        assertThat(actual).isEqualTo(inputLotteryResult);
    }

    @Test
    @DisplayName("이익률을 반환한디.")
    void getRateOfProfit() {
        //given
        TotalPurchaseAmount totalPurchaseAmount = new TotalPurchaseAmount(5000, 1000);
        Map<Rank, Integer> inputLotteryResult = new HashMap<>();
        inputLotteryResult.put(Rank.FOURTH, 1);
        inputLotteryResult.put(Rank.FIFTH, 2);
        LotteryResult lotteryResult = new LotteryResult(inputLotteryResult, totalPurchaseAmount);
        double expect = 12;

        //when
        double actual = lotteryResult.calculateRateOfProfit();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}