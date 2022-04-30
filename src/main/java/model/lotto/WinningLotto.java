package model.lotto;

import model.vo.LottoNumber;
import model.vo.Rank;

public class WinningLotto {

    private static final int COUNT_TO_HAVE_BONUS = 5;
    private final Lotto winningLotto;
    private final LottoNumber bonusBallNumber;

    public WinningLotto(final Lotto winningLotto, final LottoNumber bonusBallNumber) {
        this.winningLotto = winningLotto;
        this.bonusBallNumber = bonusBallNumber;
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
