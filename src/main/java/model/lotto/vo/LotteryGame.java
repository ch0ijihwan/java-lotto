package model.lotto.vo;

import controller.dto.LottoDto;
import model.lotto.factory.LottoFactory;
import model.purchase.vo.CountOfManualPurchase;
import model.purchase.vo.TotalPurchaseAmount;
import model.result.vo.LotteryResult;
import model.result.vo.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotteryGame {

    private static final int PRICE_OF_LOTTO = 1000;

    private final LotteryTicket lotteryTicket;
    private final TotalPurchaseAmount totalPurchaseAmount;
    private final CountOfManualPurchase countOfManualPurchase;

    public LotteryGame(final int totalPurchaseAmount, final List<LottoDto> informationOfManualLottos) {
        this.totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmount, PRICE_OF_LOTTO);
        this.countOfManualPurchase = new CountOfManualPurchase(informationOfManualLottos.size(), this.totalPurchaseAmount.getTotalPurchaseAmount());
        this.lotteryTicket = new LotteryTicket(initializePreprocessedLotteryTicket(getCountOfAutoPurchase(), informationOfManualLottos));
    }


    private List<Lotto> initializePreprocessedLotteryTicket(final int countOfAutoLotto, final List<LottoDto> informationOfManualLottos) {
        List<Lotto> autoLottos = createAutoLottos(countOfAutoLotto);
        List<Lotto> manualLottos = createManualLottos(informationOfManualLottos);
        return joinLottos(autoLottos, manualLottos);
    }

    private List<Lotto> createAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(LottoFactory::createAutoLotto)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Lotto> createManualLottos(final List<LottoDto> manualLottosInput) {
        return manualLottosInput.stream()
                .map(manualLotto -> LottoFactory.createManualLotto(manualLotto.getLottoNumbers()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Lotto> joinLottos(final List<Lotto> autoLottos, final List<Lotto> manualLottos) {
        return Stream.concat(autoLottos.stream(), manualLottos.stream())
                .collect(Collectors.toUnmodifiableList());
    }

    public int getCountOfAutoPurchase() {
        return totalPurchaseAmount.calculateCountOfTotalPurchase() - countOfManualPurchase.getCountOfManualPurchase();
    }

    public List<Lotto> getInformationOfLottos() {
        return lotteryTicket.getLotteryTicket()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }


    public int getCountOfManualPurchase() {
        return countOfManualPurchase.getCountOfManualPurchase();
    }

    public LotteryResult getLotteryResult(final LastWinningLotto lastWinningLotto) {
        List<Rank> matchingResult = lotteryTicket.getMatchingResult(lastWinningLotto);
        Map<Rank, Integer> rankAndFrequency = matchingResult.stream()
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> Collections.frequency(matchingResult, rank),
                        ((rankOfAlreadyExists, addedRank) -> rankOfAlreadyExists)));
        return new LotteryResult(rankAndFrequency, totalPurchaseAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryGame that = (LotteryGame) o;
        return Objects.equals(lotteryTicket, that.lotteryTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteryTicket);
    }
}