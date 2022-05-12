package model.lottoinformationinputter;

import model.vo.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManualLottoInjector {
    private int manualCount;
    private final List<LottoNumbers> manualLottoNumberFactories = new ArrayList<>();

    public ManualLottoInjector(final int manualCount) {
        this.manualCount = manualCount;
    }

    public int getManualRemainingCount() {
        return manualCount;
    }

    public void add(final List<Integer> inputLottoNumbers) {
        manualLottoNumberFactories.add(LottoNumbers.createManualLottoNumbers(inputLottoNumbers));
        manualCount = manualCount - 1;
    }

    public List<LottoNumbers> getManualLottos() {
        return Collections.unmodifiableList(manualLottoNumberFactories);
    }
}
