package vo;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final Map<Integer, LottoNumber> CACHED_ALL_LOTTO_NUMBERS = createCachedAllLottoNumbers();

    private final int value;

    private static Map<Integer, LottoNumber> createCachedAllLottoNumbers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toUnmodifiableMap(number -> number, LottoNumber::new));
    }

    public static LottoNumber valueOf(final int value) {
        validateRange(value);
        return CACHED_ALL_LOTTO_NUMBERS.get(value);
    }

    private static void validateRange(int value) {
        if (value < MIN_LOTTO_NUMBER || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("로또 넘버는 %d에서 %d 까지어야 합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    private LottoNumber(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
