package model;

import model.vo.LottoNumbersFactory;
import model.vo.LottoNumber;
import model.vo.Rank;

public class WinningLotto {

    private static final int COUNT_TO_HAVE_BONUS = 5;
    private final LottoNumbersFactory winningLottoNumbersFactory;
    private final LottoNumber bonusBallNumber;

    public WinningLotto(final LottoNumbersFactory winningLottoNumbersFactory, final LottoNumber bonusBallNumber) {
        validateDuplication(winningLottoNumbersFactory, bonusBallNumber);
        this.winningLottoNumbersFactory = winningLottoNumbersFactory;
        this.bonusBallNumber = bonusBallNumber;
    }

    private void validateDuplication(final LottoNumbersFactory winningLottoNumbersFactory, final LottoNumber bonusBallNumber) {
        if (hasSameLottoNumber(winningLottoNumbersFactory, bonusBallNumber)) {
            throw new IllegalArgumentException("보너스 넘버와 당첨 넘버 중 중복이 있습니다.");
        }
    }

    private boolean hasSameLottoNumber(final LottoNumbersFactory winningLottoNumbersFactory, final LottoNumber bonusBallNumber) {
        return winningLottoNumbersFactory.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }

    public Rank measureRank(final LottoNumbersFactory lottoNumbersFactory) {
        int matchingLottoNumber = lottoNumbersFactory.measureMatchingLottoNumber(winningLottoNumbersFactory);
        boolean isBonus = (matchingLottoNumber == COUNT_TO_HAVE_BONUS) && hasBonusLottoNumber(lottoNumbersFactory);
        return Rank.valueOf(matchingLottoNumber, isBonus);
    }

    private boolean hasBonusLottoNumber(final LottoNumbersFactory lottoNumbersFactory) {
        return lottoNumbersFactory.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusBallNumber));
    }
}
