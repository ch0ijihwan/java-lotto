package view.input;


import model.dto.LottoDto;

import java.util.List;

public interface Input {
    int inputTotalPurchaseAmount();

    int inputBonusNumber();

    int inputCountOfManualPurchase();

    List<LottoDto> inputManualLottoNumbers(int countOfManualPurchase);

    List<Integer> inputWiningLotto();
}