package model.vo;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;
    private static final Map<Integer, LottoNumber> CACHE_LOTTO_NUMBERS = createCacheForLottoNumbers();

    private final int value;

    private static Map<Integer, LottoNumber> createCacheForLottoNumbers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE)
                .boxed()
                .collect(Collectors.toUnmodifiableMap(key -> key, LottoNumber::new));
    }

    public static LottoNumber valueOf(final int value) {
        validateRange(value);
        return CACHE_LOTTO_NUMBERS.get(value);
    }

    private static void validateRange(final int number) {
        if (number < MIN_LOTTO_NUMBER_VALUE || number > MAX_LOTTO_NUMBER_VALUE) {
            throw new IllegalArgumentException(String.format("로또 넘버를 생성 할 수 없습니다. 로또 넘버는 %d ~ %d 사이의 숫자여야 합니다.", MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE));

        }
    }

    private LottoNumber(final int number) {
        this.value = number;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber number = (LottoNumber) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "value=" + value +
                '}';
    }
}
