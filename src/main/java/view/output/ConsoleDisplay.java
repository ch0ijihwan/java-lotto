package view.output;

import model.vo.LottoNumbersFactory;
import model.vo.LottoNumber;
import model.vo.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleDisplay implements Display {

    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구매하였습니다. %n";
    private static final String RESULT_OF_LOTTO_MESSAGE = "%d 개 일치 %s ( %d 원) - %d %n";
    private static final String DISPLAY_RATE_OF_PROFIT_MESSAGE = "총 수익률은 %f 입니다.";
    private static final String BONUS_MATCHING_MESSAGE = ", 보너스 일치";
    private static final String WINNING_STATISTICS = "당첨 통계";
    private static final String MULTI_BARS = "----------";
    private static final String LOTTO_FORMAT = "[%s] %n";
    private static final String LOTTO_DELIMITER = ",";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요";

    @Override
    public void displayDetailsOfLottoPurchased(final List<LottoNumbersFactory> lottoNumbersFactoryList) {
        System.out.printf(LOTTO_COUNT_MESSAGE, lottoNumbersFactoryList.size());
        for (LottoNumbersFactory lotto : lottoNumbersFactoryList) {
            String lottoNumbers = lotto.getLottoNumbers()
                    .stream()
                    .map(LottoNumber::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(LOTTO_DELIMITER));
            System.out.printf(LOTTO_FORMAT, lottoNumbers);
        }
    }

    @Override
    public void displayLottoTotalResult(final Map<Rank, Integer> lottoGameResult) {
        System.out.println(WINNING_STATISTICS);
        System.out.println(MULTI_BARS);
        List<Rank> sortedRanks = Rank.getSortedRanks();
        for (Rank rank : sortedRanks) {
            displayLottoResult(rank, lottoGameResult.getOrDefault(rank, 0));
        }
    }

    private void displayLottoResult(final Rank rank, final int countResult) {
        String bonusMessage = " ";
        if (rank == Rank.SECOND) {
            bonusMessage = BONUS_MATCHING_MESSAGE;
        }
        System.out.printf(RESULT_OF_LOTTO_MESSAGE, rank.getMatchingLottoNumber(), bonusMessage, rank.getDividend(), countResult);
    }

    @Override
    public void displayRateOfProfit(final double profit) {
        System.out.printf(DISPLAY_RATE_OF_PROFIT_MESSAGE, profit);
    }

    @Override
    public void displayMessageAboutInputManualLottoNumbers() {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
    }
}
