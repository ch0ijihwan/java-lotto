package model;

import model.vo.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoInjector {
    private final int manualCount;
    private final List<Lotto> manualLottos = new ArrayList<>();

    public ManualLottoInjector(final int manualCount) {
        this.manualCount = manualCount;
    }

    public int getManualRemainingCount() {
        return manualCount;
    }

    public void add(final List<Integer> inputLottoNumbers) {
        manualLottos.add(Lotto.createManualLottoNumbers(inputLottoNumbers));
    }

    public List<Lotto> getManualLottos() {
        return Collections.unmodifiableList(manualLottos);
    }
}
