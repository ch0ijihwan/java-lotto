package model;

import model.vo.Lotto;
import model.vo.Money;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private final Money money;
    private final int numberOfManualLotto;
    private final List<Lotto> manualLottos;

    public LottoTicket(final Money money, final int numberOfManualLotto, final List<List<Integer>> numbers) {
        this.money = money;
        this.numberOfManualLotto = numberOfManualLotto;
        this.manualLottos = createManualLottos(numbers);
    }

    private List<Lotto> createManualLottos(final List<List<Integer>> numbers) {
        return numbers.stream()
                .map(Lotto::createManualLottoNumbers)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Lotto> getManualLottos() {
        return manualLottos;
    }

    public int getNumberOfAutoLotto() {
        return money.getChanceToBuyLotto() - numberOfManualLotto;
    }

    public int getNumberOfManualLotto() {
        return numberOfManualLotto;
    }

}
