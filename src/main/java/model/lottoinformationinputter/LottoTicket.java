package model.lottoinformationinputter;

import model.Lottos;
import model.vo.LottoNumbers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    private final Lottos totalLottos;

    public LottoTicket(final int countOfAutoLotto, final List<LottoNumbers> manualLottoNumbers) {
        List<LottoNumbers> autoLottoNumbers = generateAutoLottos(countOfAutoLotto);
        List<LottoNumbers> joinedLottoNumbers = joinLottos(manualLottoNumbers, autoLottoNumbers);
        this.totalLottos = new Lottos(joinedLottoNumbers);
    }

    private List<LottoNumbers> generateAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(LottoNumbers::createAutoLottoNumbers)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumbers> joinLottos(final List<LottoNumbers> manualLottoNumbers, final List<LottoNumbers> autoLottoNumbers) {
        return Stream.of(autoLottoNumbers, manualLottoNumbers)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos getLottos() {
        return totalLottos;
    }
}
