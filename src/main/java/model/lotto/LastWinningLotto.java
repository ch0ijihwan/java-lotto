package model.lotto;

import java.util.Objects;

public class LastWinningLotto {

    private static final int COUNT_FOR_BONUS_NUMBER_DETERMINATION = 5;

    private final Lotto winningLotto;
    private final LottoNumber bonsNumber;

    public LastWinningLotto(final Lotto winningLottoInput, final LottoNumber bonusNumber) {
        validateDuplicationWithBonusNumber(winningLottoInput, bonusNumber);
        this.winningLotto = winningLottoInput;
        this.bonsNumber = bonusNumber;
    }

    private void validateDuplicationWithBonusNumber(final Lotto winningLottoInput, final LottoNumber bonusNumber) {
        if (isAnyDuplication(winningLottoInput, bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버와 당첨 로또 넘버는 중복 될 수 없습니다.");
        }
    }

    private boolean isAnyDuplication(final Lotto winningLottoInput, final LottoNumber bonusNumber) {
        return winningLottoInput.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    public Rank judgeRank(final Lotto lottoToCompare) {
        int matchingCount = this.winningLotto.match(lottoToCompare);
        return Rank.valueOf(matchingCount, getBonusStatus(lottoToCompare, matchingCount));
    }

    private boolean getBonusStatus(final Lotto lottoToCompare, final int matchingCount) {
        return (matchingCount == COUNT_FOR_BONUS_NUMBER_DETERMINATION) && hasBonusNumber(lottoToCompare);
    }

    private boolean hasBonusNumber(final Lotto lottoToCompare) {
        return lottoToCompare.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonsNumber));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastWinningLotto that = (LastWinningLotto) o;
        return Objects.equals(winningLotto, that.winningLotto) && Objects.equals(bonsNumber, that.bonsNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningLotto, bonsNumber);
    }
}
