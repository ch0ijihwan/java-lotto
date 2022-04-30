package model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private static final int DEFAULT_MONEY = 0;
    private final List<Lotto> lottos;
    private Money totalProfit = new Money(DEFAULT_MONEY);

    public Lottos(final Money money) {
        lottos = addAutoLotto(money.getChanceToBuyLotto());
    }

    private List<Lotto> addAutoLotto(final int numberOfChance) {
        return Stream.generate(Lotto::createAutoLottoNumbers)
                .limit(numberOfChance)
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> countLottoRanks(final WinningLotto winningLotto) {
        Map<Rank, Integer> lottoResults = new EnumMap<>(Rank.class);
        List<Rank> resultRanks = measureRanks(winningLotto);
        for (Rank targetRank : Rank.values()) {
            int frequencyOfTargetRank = countRankFrequency(lottoResults, resultRanks, targetRank);
            totalProfit = new Money(totalProfit.getMoney() + targetRank.getDividend() * frequencyOfTargetRank);
        }
        return lottoResults;
    }

    private List<Rank> measureRanks(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableList());
    }

    private int countRankFrequency(Map<Rank, Integer> lottoResults, List<Rank> resultRanks, Rank targetRank) {
        int frequencyOfTargetRank = Collections.frequency(resultRanks, targetRank);
        lottoResults.put(targetRank, frequencyOfTargetRank);
        return frequencyOfTargetRank;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Money getToTalProfit() {
        return totalProfit;
    }
}
