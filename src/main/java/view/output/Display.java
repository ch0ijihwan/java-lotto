package view.output;

import model.vo.LottoNumbersFactory;
import model.vo.Rank;

import java.util.List;
import java.util.Map;

public interface Display {
    void displayDetailsOfLottoPurchased(List<LottoNumbersFactory> lottoNumbersFactoryList);

    void displayLottoTotalResult(Map<Rank, Integer> lottoGameResult);

    void displayRateOfProfit(double profit);

    void displayMessageAboutInputManualLottoNumbers();
}
