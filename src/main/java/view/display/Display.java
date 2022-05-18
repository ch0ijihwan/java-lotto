package view.display;

import controller.dto.LottoDto;
import model.result.vo.Rank;

import java.util.List;
import java.util.Map;

public interface Display {

    void displayPurchaseCount(int countOfAutoPurchase, int countOfManualPurchase);

    void displayLotteryTicket(List<LottoDto> lottos);

    void displayLotteryResult(Map<Rank, Integer> lottoGameResult);

    void displayRateOfProfit(double profit);

    void displayManualNumbersMessage();
}
