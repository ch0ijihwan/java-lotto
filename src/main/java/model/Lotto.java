package model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> lottoNumbers;
    private static final List<Integer> allLottoNumbers = createAllLottoNumbers();

    private static List<Integer> createAllLottoNumbers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE)
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }


    public static Lotto createAutoLottoNumbers() {
        Collections.shuffle(allLottoNumbers);
        List<Integer> peekLottoNumbers = allLottoNumbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toUnmodifiableList());
        return new Lotto(peekLottoNumbers);
    }

    public static Lotto createLottoNumbers(final List<Integer> numbers) {
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
            throw new IllegalArgumentException("로또 넘버는 중복 될 수 업습니다.");
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    public int measureMatchingLottoNumber(final Lotto otherLottoNumbers) {
        List<LottoNumber> comparisonLottoNumberList = otherLottoNumbers.getLottoNumbers();
        return (int) lottoNumbers.stream()
                .filter(lottoNumber -> comparisonLottoNumberList.stream().anyMatch(Predicate.isEqual(lottoNumber)))
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto that = (Lotto) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return "LottoNumbers{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}
