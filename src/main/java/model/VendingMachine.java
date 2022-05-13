package model;

import model.dto.LottoDto;
import model.factory.LottoFactory;
import model.lotto.Lottos;
import model.lotto.WinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.lotto.vo.Rank;
import model.purchase.CountOfManualPurchase;
import model.purchase.TotalPurchaseAmount;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VendingMachine {

    private static final int PRICE_OF_LOTTO = 1000;

    private final Lottos lottos;
    private final TotalPurchaseAmount totalPurchaseAmount;
    private final CountOfManualPurchase countOfManualPurchase;

    public VendingMachine(final int totalPurchaseAmount, final List<LottoDto> manualLottosInput) {
        this.totalPurchaseAmount = new TotalPurchaseAmount(totalPurchaseAmount, PRICE_OF_LOTTO);
        this.countOfManualPurchase = new CountOfManualPurchase(manualLottosInput.size(), this.totalPurchaseAmount.getTotalPurchaseAmount());
        this.lottos = new Lottos(initializePreprocessedLottos(getCountOfAutoPurchase(), manualLottosInput));
    }


    private List<Lotto> initializePreprocessedLottos(final int countOfAutoLotto, final List<LottoDto> manualLottosInput) {
        List<Lotto> autoLottos = createAutoLottos(countOfAutoLotto);
        List<Lotto> manualLottos = createManualLottos(manualLottosInput);
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
        return totalPurchaseAmount.getCountOfTotalPurchase() - countOfManualPurchase.getValue();
    }

    public List<LottoDto> getInformationOfLottos() {
        return lottos.getLottos()
                .stream()
                .map(this::createLottoDto)
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoDto createLottoDto(final Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(lottoNumbers);
    }

    public int getCountOfManualPurchase() {
        return countOfManualPurchase.getValue();
    }

    public Map<Rank, Integer> getTotalResultOfLotto(final WinningLotto winningLotto) {
        List<Rank> matchingResult = lottos.getMatchingResult(winningLotto);
        return matchingResult.stream()
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> getFrequency(matchingResult, rank)));
    }

    private int getFrequency(final List<Rank> matchingResult, final Rank rank) {
        return Collections.frequency(matchingResult, rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendingMachine that = (VendingMachine) o;
        return Objects.equals(lottos, that.lottos) && Objects.equals(totalPurchaseAmount, that.totalPurchaseAmount) && Objects.equals(countOfManualPurchase, that.countOfManualPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos, totalPurchaseAmount, countOfManualPurchase);
    }
}
