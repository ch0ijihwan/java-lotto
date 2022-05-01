package model.lotto;

import model.vo.Rank;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(final int numberOfCanBuyLotto) {
        lottos = addAutoLotto(numberOfCanBuyLotto);
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
            countFrequencyOfTargetRank(lottoResults, resultRanks, targetRank);
        }
        return lottoResults;
    }

    private void countFrequencyOfTargetRank(final Map<Rank, Integer> lottoResults, final List<Rank> resultRanks, final Rank targetRank) {
        int frequencyOfTargetRank = Collections.frequency(resultRanks, targetRank);
        lottoResults.put(targetRank, frequencyOfTargetRank);
    }

    private List<Rank> measureRanks(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
