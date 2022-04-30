package view.output;

import model.Lotto;
import model.Rank;

import java.util.List;
import java.util.Map;

public interface Display {
    void displayDetailsOfLottoPurchased(List<Lotto> lottoList);

    void displayLottoTotalResult(Map<Rank, Integer> lottoGameResult);

    void displayRateOfProfit(double profit);
}
