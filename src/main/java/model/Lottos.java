package model;

import model.vo.Lotto;
import model.vo.Rank;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Map<Rank, Integer> countLottoRanks(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> getFrequency(winningLotto, rank), (rank1, rank2) -> rank1));
    }

    private int getFrequency(final WinningLotto winningLotto, final Rank rank) {
        return Collections.frequency(measureRanks(winningLotto), rank);
    }

    private List<Rank> measureRanks(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos otherlottos = (Lottos) o;
        return lottos.containsAll(otherlottos.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
