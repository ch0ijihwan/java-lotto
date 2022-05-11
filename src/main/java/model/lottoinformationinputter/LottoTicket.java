package model.lottoinformationinputter;

import model.Lottos;
import model.vo.LottoNumbersFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    private final Lottos totalLottos;

    public LottoTicket(final int countOfAutoLotto, final List<LottoNumbersFactory> manualLottoNumberFactories) {
        List<LottoNumbersFactory> autoLottoNumberFactories = generateAutoLottos(countOfAutoLotto);
        List<LottoNumbersFactory> joinedLottoNumberFactories = joinLottos(manualLottoNumberFactories, autoLottoNumberFactories);
        this.totalLottos = new Lottos(joinedLottoNumberFactories);
    }

    private List<LottoNumbersFactory> generateAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(LottoNumbersFactory::createAutoLottoNumbers)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumbersFactory> joinLottos(final List<LottoNumbersFactory> manualLottoNumberFactories, final List<LottoNumbersFactory> autoLottoNumberFactories) {
        return Stream.of(autoLottoNumberFactories, manualLottoNumberFactories)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos getLottos() {
        return totalLottos;
    }
}
