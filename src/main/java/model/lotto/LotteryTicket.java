package model.lotto;

import model.lotto.vo.Lotto;
import model.lotto.vo.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LotteryTicket {

    private final List<Lotto> lotteryTicket;

    public LotteryTicket(final List<Lotto> lotteryTicket) {
        this.lotteryTicket = lotteryTicket;
    }


    public List<Lotto> getLotteryTicket() {
        return new ArrayList<>(lotteryTicket);
    }

    public List<Rank> getMatchingResult(final WinningLotto winningLotto) {
        return lotteryTicket.stream()
                .map(winningLotto::judgeRank)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryTicket otherLotteryTicket = (LotteryTicket) o;
        return lotteryTicket.containsAll(otherLotteryTicket.lotteryTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotteryTicket);
    }
}
