package model;

import model.vo.Lotto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    private final Lottos totalLottos;

    public LottoTicket(final int countOfAutoLotto, final List<Lotto> manualLottos) {
        List<Lotto> autoLottos = generateAutoLottos(countOfAutoLotto);
        List<Lotto> joinedLottos = joinLottos(manualLottos, autoLottos);
        this.totalLottos = new Lottos(joinedLottos);
    }

    private List<Lotto> generateAutoLottos(final int countOfAutoLotto) {
        return Stream.generate(Lotto::createAutoLottoNumbers)
                .limit(countOfAutoLotto)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Lotto> joinLottos(final List<Lotto> manualLottos, final List<Lotto> autoLottos) {
        return Stream.of(autoLottos, manualLottos)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }

    public Lottos getLottos() {
        return totalLottos;
    }
}
