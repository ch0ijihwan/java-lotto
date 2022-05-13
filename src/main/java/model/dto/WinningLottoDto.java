package model.dto;

import java.util.Collections;
import java.util.List;

public class WinningLottoDto {
    private final List<Integer> lottoNumbers;
    private final int bonusNumber;

    public WinningLottoDto(final List<Integer> lottoNumbers, final int bonusNumber) {
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}