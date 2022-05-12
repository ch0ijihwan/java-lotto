package vo;

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

    private static final int SECOND_OR_THIRD_MATCHING_COUNT = 5;

    private final int matchingCount;
    private final int reward;

    Rank(final int matchingCount, final int reward) {
        this.matchingCount = matchingCount;
        this.reward = reward;
    }

    public static Rank valueOf(final int matchingCount, final boolean bonus) {
        if (matchingCount == SECOND_OR_THIRD_MATCHING_COUNT) {
            return gradeRankByBonus(bonus);
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchingCount == matchingCount)
                .findFirst()
                .orElse(LAST);
    }

    private static Rank gradeRankByBonus(final boolean bonus) {
        if (bonus) {
            return SECOND;
        }
        return THIRD;
    }

    public static List<Rank> getSortedRanks() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(Rank::getReward))
                .filter(rank -> rank != LAST)
                .collect(Collectors.toUnmodifiableList());
    }

    public int getReward() {
        return reward;
    }

    public int getMatchingCount() {
        return matchingCount;
    }
}
