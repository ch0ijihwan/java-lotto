package model.lotto.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static model.lotto.vo.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @DisplayName("매칭하는 로또 번호 숫자 갯수와 보너스볼 매칭 여부를 입력 받아, 해당하는 랭크를 반환한다.")
    @MethodSource("createRankValueOfParameterProvider")
    void valueOf(final int matchingCount, final boolean bonus, final Rank expect) {
        //when
        Rank actual = Rank.valueOf(matchingCount, bonus);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    static Stream<Arguments> createRankValueOfParameterProvider() {
        return Stream.of(
                Arguments.of(6, true, FIRST),
                Arguments.of(6, false, FIRST),
                Arguments.of(5, true, SECOND),
                Arguments.of(5, false, THIRD),
                Arguments.of(4, true, FOURTH),
                Arguments.of(3, true, FIFTH),
                Arguments.of(2, true, LAST)
        );
    }

    @Test
    @DisplayName("해당하는 등수의 상금을 반환한다.")
    void getReward() {
        //given
        int expect = 5_000;

        //when
        int actual = FIFTH.getReward();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("정렬된 등수들을 반환한다.")
    void getSortedRanks() {
        //given
        List<Rank> ranks = List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);

        //when
        List<Rank> actual = Rank.getSortedRanks();

        //then
        assertThat(ranks).isEqualTo(actual);
    }

    @Test
    @DisplayName("매칭 횟수를 반환한다.")
    void getMatchingCount() {
        //given
        int expect = 3;

        //when
        int actual = FIFTH.getMatchingCount();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}