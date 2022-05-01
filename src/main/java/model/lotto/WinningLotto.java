package model.lotto;

import model.vo.LottoNumber;
import model.vo.Rank;

public class WinningLotto {

    private static final int COUNT_TO_HAVE_BONUS = 5;
    private final Lotto winningLotto;
    private final LottoNumber bonusBallNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusBallNumber) {
        validateDuplication(winningLotto, bonusBallNumber);
        this.winningLotto = winningLotto;
        this.bonusBallNumber = bonusBallNumber;
    }

    private void validateDuplication(final Lotto winningLotto, final LottoNumber bonusBallNumber) {
        if (hasSameLottoNumber(winningLotto, bonusBallNumber)) {
            throw new IllegalArgumentException("보너스 넘버와 당첨 넘버 중 중복이 있습니다.");
        }
    }

    private boolean hasSameLottoNumber(final Lotto winningLotto, final LottoNumber bonusBallNumber) {
        return winningLotto.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }

    public Rank measureRank(final Lotto lotto) {
        int matchingLottoNumber = lotto.measureMatchingLottoNumber(winningLotto);
        boolean isBonus = (matchingLottoNumber == COUNT_TO_HAVE_BONUS) && hasBonusLottoNumber(lotto);
        return Rank.valueOf(matchingLottoNumber, isBonus);
    }

    private boolean hasBonusLottoNumber(final Lotto lotto) {
        return lotto.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }
}
