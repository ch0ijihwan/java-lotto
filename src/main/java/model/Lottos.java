package model;

import model.vo.LottoNumbers;
import model.vo.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {

    private final List<LottoNumbers> lottoNumberFactories;

    public Lottos(final List<LottoNumbers> lottoNumberFactories) {
        this.lottoNumberFactories = lottoNumberFactories;
    }

    public Map<Rank, Integer> countLottoRanks(final WinningLotto winningLotto) {
        return lottoNumberFactories.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> getFrequency(winningLotto, rank), (rank1, rank2) -> rank1));
    }

    private int getFrequency(final WinningLotto winningLotto, final Rank rank) {
        return Collections.frequency(measureRanks(winningLotto), rank);
    }

    private List<Rank> measureRanks(final WinningLotto winningLotto) {
        return lottoNumberFactories.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoNumbers> getLottos() {
        return Collections.unmodifiableList(lottoNumberFactories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos otherlottos = (Lottos) o;
        return lottoNumberFactories.containsAll(otherlottos.lottoNumberFactories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumberFactories);
    }
}
