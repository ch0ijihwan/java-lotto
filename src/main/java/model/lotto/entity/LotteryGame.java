package model.lotto.entity;

import model.lotto.factory.LottoFactory;
import model.lotto.vo.LastWinningLotto;
import model.lotto.vo.LotteryTicket;
import model.lotto.vo.Lotto;
import model.purchase.vo.CountOfManualPurchase;
import model.purchase.vo.TotalPurchaseAmount;
import model.result.vo.LotteryResult;
import model.result.vo.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotteryGame {

    private static final int PRICE_OF_LOTTO = 1000;

    private LotteryTicket lotteryTicket;
    private CountOfManualPurchase countOfManualPurchase;
    private final TotalPurchaseAmount totalPurchaseAmount;

    public LotteryGame(final int totalPurchaseAmount, final int countOfManualLottos) {
        this.totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmount, PRICE_OF_LOTTO);
        this.countOfManualPurchase = new CountOfManualPurchase(countOfManualLottos, this.totalPurchaseAmount.getTotalPurchaseAmount());
        int countOfAutoLottos = this.totalPurchaseAmount.calculateCountOfTotalPurchase() - countOfManualLottos;
        this.lotteryTicket = new LotteryTicket(createAutoLottos(countOfAutoLottos));
    }

    private List<Lotto> createAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(LottoFactory::createAutoLotto)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getCountOfAutoPurchase() {
        return totalPurchaseAmount.calculateCountOfTotalPurchase() - countOfManualPurchase.getCountOfManualPurchase();
    }

    public int getCountOfManualPurchase() {
        return this.totalPurchaseAmount.calculateCountOfTotalPurchase() - getCountOfAutoPurchase();
    }

    public List<Lotto> getInformationOfLottos() {
        return lotteryTicket.getLotteryTicket()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean hasCountOfManualLottos() {
        return countOfManualPurchase.getCountOfManualPurchase() > 0;
    }

    public void addManualLotto(final Lotto manualLotto) {
        this.countOfManualPurchase = countOfManualPurchase.decrease();
        this.lotteryTicket = lotteryTicket.addLotto(manualLotto);
    }

    public LotteryResult getLotteryResult(final LastWinningLotto lastWinningLotto) {
        List<Rank> matchingResult = lotteryTicket.getMatchingResult(lastWinningLotto);
        Map<Rank, Integer> rankAndFrequency = matchingResult.stream()
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> Collections.frequency(matchingResult, rank),
                        ((rankOfAlreadyExists, addedRank) -> rankOfAlreadyExists)));
        return new LotteryResult(rankAndFrequency, totalPurchaseAmount);
    }
}
