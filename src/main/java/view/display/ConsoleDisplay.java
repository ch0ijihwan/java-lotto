package view.display;

import controller.dto.LottoDto;
import model.result.vo.Rank;

import java.util.List;
import java.util.Map;

public class ConsoleDisplay implements Display {

    private static final int NON_MATCHED = 0;
    private static final int SECOND_THRESHOLD = 0;
    private static final String COUNT_OF_LOTTO_MESSAGE = "자동 로또 %d개, 수동 로또 %d개를 구매하였습니다.\n";
    private static final String RESULT_OF_MATCHING_LOTTO_MESSAGE = "%d 개 일치 %s ( %d 원) - %d \n";
    private static final String RATE_OF_PROFIT_MESSAGE = "총 수익률은 %f 입니다.\n";
    private static final String BONUS_MATCHING_MESSAGE = ", 보너스 일치";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String MULTI_BARS = "----------";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요";

    @Override
    public void displayPurchaseCount(final int countOfAutoPurchase, final int countOfManualPurchase) {
        System.out.printf(COUNT_OF_LOTTO_MESSAGE, countOfAutoPurchase, countOfManualPurchase);
    }

    @Override
    public void displayLotteryTicket(final List<LottoDto> lottos) {
        lottos.forEach(lotto -> System.out.println(lotto.getLottoNumbers()));
    }

    @Override
    public void displayLotteryResult(final Map<Rank, Integer> lotteryResult) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(MULTI_BARS);
        List<Rank> sortedRanks = Rank.getSortedRanks();
        for (Rank rank : sortedRanks) {
            displayLottoResult(rank, lotteryResult.getOrDefault(rank, NON_MATCHED));
        }
    }

    private void displayLottoResult(final Rank rank, final int matchedCount) {
        String bonusMessage = " ";
        if (rank == Rank.SECOND && matchedCount == SECOND_THRESHOLD) {
            bonusMessage = BONUS_MATCHING_MESSAGE;
        }
        System.out.printf(RESULT_OF_MATCHING_LOTTO_MESSAGE, rank.getCountOfBingo(), bonusMessage, rank.getReward(), matchedCount);
    }

    @Override
    public void displayRateOfProfit(double profit) {
        System.out.printf(RATE_OF_PROFIT_MESSAGE, profit);
    }

    @Override
    public void displayManualNumbersMessage() {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
    }
}
