package model.lotto;

import model.vo.LottoNumber;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lotto {

    private static final Random RANDOM = new Random();
    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final Set<Integer> RANDOM_LOTTO_NUMBER_VALUES = createRandomLottoNumberValues();

    private final List<LottoNumber> lottoNumbers;

    private static Set<Integer> createRandomLottoNumberValues() {
        return Stream.generate(() -> RANDOM.nextInt(MAX_LOTTO_NUMBER_VALUE) + MIN_LOTTO_NUMBER_VALUE)
                .distinct()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toUnmodifiableSet());
    }

    public static Lotto createAutoLottoNumbers() {
        List<Integer> peekLottoNumbers = RANDOM_LOTTO_NUMBER_VALUES.stream()
                .collect(Collectors.toUnmodifiableList());
        return new Lotto(peekLottoNumbers);
    }

    public static Lotto createManualLottoNumbers(final List<Integer> numbers) {
        return new Lotto(numbers);
    }

    private Lotto(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplication(numbers);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format("로또 넘버는 %d 가지어야 합니다.", LOTTO_NUMBERS_SIZE));
        }
    }

    private void validateDuplication(final List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException("로또 넘버는 중복 될 수 없습니다.");
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int measureMatchingLottoNumber(final Lotto otherLottoNumbers) {
        List<LottoNumber> comparisonLottoNumberList = otherLottoNumbers.getLottoNumbers();
        return (int) lottoNumbers.stream()
                .filter(lottoNumber -> isAnyMatch(comparisonLottoNumberList, lottoNumber))
                .count();
    }

    private boolean isAnyMatch(final List<LottoNumber> comparisonLottoNumberList, final LottoNumber lottoNumber) {
        return comparisonLottoNumberList.stream()
                .anyMatch(Predicate.isEqual(lottoNumber));
    }
}
