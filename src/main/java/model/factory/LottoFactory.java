package model.factory;

import model.Lotto;
import vo.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
    private static final int MIN_LOTTO_NUMBER_VALUE = 1;
    private static final int MAX_LOTTO_NUMBER_VALUE = 45;
    private static final int LOTTO_SIZE = 6;
    private static final List<Integer> CACHED_ALL_LOTTO_NUMBER_VALUES = createCachedAllLottoNumberValues();

    private static List<Integer> createCachedAllLottoNumberValues() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER_VALUE, MAX_LOTTO_NUMBER_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    public static Lotto createAutoLotto() {
        Collections.shuffle(CACHED_ALL_LOTTO_NUMBER_VALUES);
        List<LottoNumber> peekedLottoNumbers = CACHED_ALL_LOTTO_NUMBER_VALUES.subList(0, LOTTO_SIZE)
                .stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
        return new Lotto(peekedLottoNumbers);
    }

    public static Lotto createManualLotto(final List<Integer> manualLottoNumbers) {
        List<LottoNumber> lottoNumbers = manualLottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableList());
        return new Lotto(lottoNumbers);
    }

    private LottoFactory() {
    }
}