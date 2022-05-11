package model.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersFactory {

    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final List<Integer> All_LOTTO_NUMBER_VALUES = createAllLottoNumberValues();

    private final List<LottoNumber> lottoNumbers;

    private static List<Integer> createAllLottoNumberValues() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    public static LottoNumbersFactory createAutoLottoNumbers() {
        Collections.shuffle(All_LOTTO_NUMBER_VALUES);
        List<Integer> peekLottoNumbers = All_LOTTO_NUMBER_VALUES.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toUnmodifiableList());
        return new LottoNumbersFactory(peekLottoNumbers);
    }

    public static LottoNumbersFactory createManualLottoNumbers(final List<Integer> numbers) {
        return new LottoNumbersFactory(numbers);
    }

    private LottoNumbersFactory(final List<Integer> numbers) {
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

    public int measureMatchingLottoNumber(final LottoNumbersFactory otherLottoNumbersNumbersFactory) {
        List<LottoNumber> comparisonLottoNumberList = otherLottoNumbersNumbersFactory.getLottoNumbers();
        return (int) lottoNumbers.stream()
                .filter(lottoNumber -> isAnyMatch(comparisonLottoNumberList, lottoNumber))
                .count();
    }

    private boolean isAnyMatch(final List<LottoNumber> comparisonLottoNumberList, final LottoNumber lottoNumber) {
        return comparisonLottoNumberList.stream()
                .anyMatch(Predicate.isEqual(lottoNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbersFactory lottoNumbersFactory = (LottoNumbersFactory) o;
        return Objects.equals(this.lottoNumbers, lottoNumbersFactory.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}