package model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @Test
    @DisplayName("초기 투자금과 로또 결과를 받아, 이익을 계산하고 이익률을 반환한다.")
    void calculateProfit() {
        //given
        Money initialMoney = new Money(200000);
        Map<Rank, Integer> lottoGameResult = new HashMap<>();
        lottoGameResult.put(Rank.FIFTH, 1);
        lottoGameResult.put(Rank.FOURTH, 2);
        Profit profit = new Profit(initialMoney, lottoGameResult);
        double expect = 0.525;

        //when
        double actual = profit.getRateOfProfit();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}