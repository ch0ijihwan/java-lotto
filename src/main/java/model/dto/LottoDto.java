package model.dto;

import java.util.Collections;
import java.util.List;

public class LottoDto {
    private final List<Integer> LottoNumbers;

    public LottoDto(final List<Integer> lottoNumbers) {
        LottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(LottoNumbers);
    }
}
