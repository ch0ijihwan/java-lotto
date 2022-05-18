package model.lotto.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(final List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplication(lottoNumbers);
        this.lottoNumbers = sortLottoNumbers(lottoNumbers);
    }

    private List<LottoNumber> sortLottoNumbers(final List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplication(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("로또 안의 로또 넘버는 6가지어야 합니다.");
        }
    }

    private void validateSize(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또안의 로또 넘버는 중복이 될 수 없습니다.");
        }
    }

    public int match(final Lotto otherLotto) {
        return (int) lottoNumbers.stream()
                .filter(otherLotto.lottoNumbers::contains)
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
