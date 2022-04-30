package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;
    private Money totalProfit = new Money(0);

    public Lottos(final Money money) {
        lottos = addAutoLotto(money.getChangeToBuyLotto());
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
        Map<Rank, Integer> lottoResults = new HashMap<>();
        List<Rank> resultRanks = measureRanks(winningLotto);
        for (Rank targetRank : Rank.values()) {
            int frequencyOfTargetRank = Collections.frequency(resultRanks, targetRank);
            lottoResults.put(targetRank, frequencyOfTargetRank);
            totalProfit = new Money(totalProfit.getMoney() + targetRank.getDividend() * frequencyOfTargetRank);
        }
        return lottoResults;
    }

    private List<Rank> measureRanks(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public Money getToTalProfit() {
        return totalProfit;
    }
}
