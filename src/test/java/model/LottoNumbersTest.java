package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoNumbersTest {

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private Lotto lottoNumbers;

    @Test
    @DisplayName("입력 받은 값에 해당하는 로또 넘버를 생성한다.")
    void createLottoNumbers() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<LottoNumber> expect = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        //when
        Lotto actual = Lotto.createLottoNumbers(numbers);

        //then
        assertThat(actual).extracting("lottoNumbers")
                .isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("로또 넘버의 갯수가 6 가지가 아니라면 예외처리를 반환한다.")
    @MethodSource("createNumbersParameterProvider")
    void validateLottoNumbersSize(final List<Integer> numbers) {
        //then
        assertThatIllegalArgumentException().isThrownBy(() -> Lotto.createLottoNumbers(numbers))
                .withMessage(String.format("로또 넘버는 %d 가지어야 합니다.", LOTTO_NUMBERS_SIZE));
    }

    static Stream<Arguments> createNumbersParameterProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7),
                        List.of(1)
                )
        );
    }

    @Test
    @DisplayName("입력 받은 번호들 중 중복이 있으면 예외처리를 반환한다.")
    void validateDuplication() {
        //given
        List<Integer> numbers = List.of(1, 1, 3, 4, 5, 6);

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> Lotto.createLottoNumbers(numbers));
    }

    @Test
    @DisplayName("로또 넘버를 반환한다")
    void getLottoNumbers() {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        lottoNumbers = Lotto.createLottoNumbers(numbers);
        List<LottoNumber> expect = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        //when
        List<LottoNumber> actual = lottoNumbers.getLottoNumbers();

        //then
        assertThat(actual).isEqualTo(expect);
    }


    @ParameterizedTest
    @DisplayName("입력 받은 로또 넘버와 비교하여 매칭된 넘버의 갯수를 반환한다.")
    @MethodSource("createMatchingLottoNumbersParameterProvider")
    void measureMatchingLottoNumber(final Lotto comparisonLottoNumbers, final int matchingCount) {
        //given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        Lotto lottoNumbers = Lotto.createLottoNumbers(numbers);
        List<LottoNumber> expect = List.of(
                LottoNumber.valueOf(1), LottoNumber.valueOf(2), LottoNumber.valueOf(3),
                LottoNumber.valueOf(4), LottoNumber.valueOf(5), LottoNumber.valueOf(6)
        );

        //when
        int actual = lottoNumbers.measureMatchingLottoNumber(comparisonLottoNumbers);

        //then
        assertThat(actual).isEqualTo(matchingCount);
    }

    static Stream<Arguments> createMatchingLottoNumbersParameterProvider() {
        return Stream.of(
                Arguments.of(
                        Lotto.createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)), 6,
                        Lotto.createLottoNumbers(List.of(1, 45, 44, 43, 42, 41)), 1,
                        Lotto.createLottoNumbers(List.of(1, 2, 3, 45, 44, 43)), 3)
        );
    }
}