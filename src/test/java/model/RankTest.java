package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static model.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class RankTest {
    @ParameterizedTest
    @DisplayName("매칭 번호와 보너스 값을 입력하면 그에 해당하는 Rank 를 반환한다.")
    @MethodSource("createRankValueOfParameterProvider")
    void valueOf(final int matchingNumber, final boolean matchBonus, final Rank expect) {
        //when
        Rank actual = Rank.valueOf(matchingNumber, matchBonus);

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
}
