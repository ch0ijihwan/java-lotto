package view.input;


import controller.dto.LottoDto;

import java.util.List;

public interface Input {
    int inputTotalPurchaseAmount();

    int inputBonusNumber();

    int inputCountOfManualPurchase();

    List<LottoDto> inputManualLottoNumbers(int countOfManualPurchase);

    List<Integer> inputManualLottoNumbers2();

    List<Integer> inputWiningLotto();
}