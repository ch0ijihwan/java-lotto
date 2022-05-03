package model.vo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    LAST(0, 0);

    private static final int SECOND_OR_THIRD = 5;

    private final int matchingLottoNumber;
    private final int dividend;

    Rank(final int matchingLottoNumber, final int dividend) {
        this.matchingLottoNumber = matchingLottoNumber;
        this.dividend = dividend;
    }

    public static Rank valueOf(final int matchingLottoNumber, final boolean matchedBonusNumber) {
        if (matchingLottoNumber == SECOND_OR_THIRD) {
            return judgeRankByMatchedBonusNumber(matchedBonusNumber);
        }
        return Arrays.stream(values())
                .filter(rank -> rank.getMatchingLottoNumber() == matchingLottoNumber)
                .findFirst()
                .orElse(LAST);
    }

    private static Rank judgeRankByMatchedBonusNumber(boolean matchBonus) {
        if (matchBonus) {
            return SECOND;
        }
        return THIRD;
    }

    public int getMatchingLottoNumber() {
        return matchingLottoNumber;
    }

    public int getDividend() {
        return dividend;
    }

    public static List<Rank> getSortedRanks() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(Rank::getDividend))
                .filter(rank -> rank != Rank.LAST)
                .collect(Collectors.toUnmodifiableList());
    }
}

