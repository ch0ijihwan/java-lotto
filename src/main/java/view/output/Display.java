package view.output;

import model.vo.LottoNumbers;
import model.vo.Rank;

import java.util.List;
import java.util.Map;

public interface Display {
    void displayDetailsOfLottoPurchased(List<LottoNumbers> lottoNumbersList);

    void displayLottoTotalResult(Map<Rank, Integer> lottoGameResult);

    void displayRateOfProfit(double profit);

    void displayMessageAboutInputManualLottoNumbers();
}
