package model.lotto;

import model.lotto.vo.Lotto;
import model.lotto.vo.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LotteryTicket {

    private final List<Lotto> lottos;

    public LotteryTicket(final List<Lotto> lottos) {
        this.lottos = lottos;
    }


    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public List<Rank> getMatchingResult(final WinningLotto winningLotto) {
        return lottos.stream()
                .map(winningLotto::judgeRank)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryTicket otherLotteryTicket = (LotteryTicket) o;
        return lottos.containsAll(otherLotteryTicket.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}