package model.lotto.vo;

import model.result.vo.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LotteryTicket {

    private final List<Lotto> lotteryTicket;

    public LotteryTicket(final List<Lotto> lotteryTicket) {
        this.lotteryTicket = lotteryTicket;
    }


    public List<Lotto> getLotteryTicket() {
        return new ArrayList<>(lotteryTicket);
    }

    public LotteryTicket addLotto(final Lotto lotto) {
        List<Lotto> addedLotteryTicket = Stream.concat(lotteryTicket.stream(), Stream.of(lotto))
                .collect(Collectors.toUnmodifiableList());
        return new LotteryTicket(addedLotteryTicket);
    }

    public List<Rank> getMatchingResult(final LastWinningLotto lastWinningLotto) {
        return lotteryTicket.stream()
                .map(lastWinningLotto::judgeRank)
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
