package model;

import model.vo.Lotto;
import model.vo.Rank;

import java.util.*;
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
        return lottos.stream()
                .map(winningLotto::measureRank)
                .collect(Collectors.toUnmodifiableMap(rank -> rank, rank -> getFrequency(winningLotto, rank)));
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
}
