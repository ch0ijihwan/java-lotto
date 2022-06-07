package model.lottoinformationinputter;

import model.Lottos;
import model.vo.LottoNumbers;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    private final Lottos totalLottos;

    public LottoTicket(final int countOfAutoLotto, final List<LottoNumbers> manualLottoNumberFactories) {
        List<LottoNumbers> autoLottoNumberFactories = generateAutoLottos(countOfAutoLotto);
        List<LottoNumbers> joinedLottoNumberFactories = joinLottos(manualLottoNumberFactories, autoLottoNumberFactories);
        this.totalLottos = new Lottos(joinedLottoNumberFactories);
    }

    private List<LottoNumbers> generateAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(LottoNumbers::createAutoLottoNumbers)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumbers> joinLottos(final List<LottoNumbers> manualLottoNumberFactories, final List<LottoNumbers> autoLottoNumberFactories) {
        return Stream.of(autoLottoNumberFactories, manualLottoNumberFactories)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos getLottos() {
        return totalLottos;
    }
}
