package model.lottoinformationinputter;

import model.vo.LottoNumbersFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoInjector {
    private int manualCount;
    private final List<LottoNumbersFactory> manualLottoNumberFactories = new ArrayList<>();

    public ManualLottoInjector(final int manualCount) {
        this.manualCount = manualCount;
    }

    public int getManualRemainingCount() {
        return manualCount;
    }

    public void add(final List<Integer> inputLottoNumbers) {
        manualLottoNumberFactories.add(LottoNumbersFactory.createManualLottoNumbers(inputLottoNumbers));
        manualCount = manualCount - 1;
    }

    public List<LottoNumbersFactory> getManualLottos() {
        return Collections.unmodifiableList(manualLottoNumberFactories);
    }
}
