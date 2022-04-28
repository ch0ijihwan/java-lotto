package model;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    LAST(0, 0);
    private final int matchingLottoNumber;
    private final int dividend;

    Rank(final int matchingLottoNumber, final int dividend) {
        this.matchingLottoNumber = matchingLottoNumber;
        this.dividend = dividend;
    }

    public static Rank valueOf(final int matchingLottoNumber, final boolean matchBonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.getMatchingLottoNumber() == matchingLottoNumber && (!rank.equals(SECOND) || matchBonus))
                .findFirst()
                .orElse(LAST);
    }

    public int getMatchingLottoNumber() {
        return matchingLottoNumber;
    }

    public int getDividend() {
        return dividend;
    }
}

