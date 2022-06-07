package model;

import model.vo.LottoNumbers;
import model.vo.LottoNumber;
import model.vo.Rank;

public class WinningLotto {

    private static final int COUNT_TO_HAVE_BONUS = 5;
    private final LottoNumbers winningLottoNumbers;
    private final LottoNumber bonusBallNumber;

    public WinningLotto(final LottoNumbers winningLottoNumbers, final LottoNumber bonusBallNumber) {
        validateDuplication(winningLottoNumbers, bonusBallNumber);
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusBallNumber = bonusBallNumber;
    }

    private void validateDuplication(final LottoNumbers winningLottoNumbers, final LottoNumber bonusBallNumber) {
        if (hasSameLottoNumber(winningLottoNumbers, bonusBallNumber)) {
            throw new IllegalArgumentException("보너스 넘버와 당첨 넘버 중 중복이 있습니다.");
        }
    }

    private boolean hasSameLottoNumber(final LottoNumbers winningLottoNumbers, final LottoNumber bonusBallNumber) {
        return winningLottoNumbers.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }

    public Rank measureRank(final LottoNumbers lottoNumbers) {
        int matchingLottoNumber = lottoNumbers.measureMatchingLottoNumber(winningLottoNumbers);
        boolean isBonus = (matchingLottoNumber == COUNT_TO_HAVE_BONUS) && hasBonusLottoNumber(lottoNumbers);
        return Rank.valueOf(matchingLottoNumber, isBonus);
    }

    private boolean hasBonusLottoNumber(final LottoNumbers lottoNumbers) {
        return lottoNumbers.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }
}
