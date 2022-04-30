package controller;

import model.*;
import view.input.Input;
import view.output.Display;

public class Controller {
    private final Input input;
    private final Display output;

    public Controller(Input input, Display output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        Money earlylMoney = new Money(input.inputMoney());
        Lottos lottos = new Lottos(earlylMoney);
        output.displayDetailsOfLottoPurchased(lottos.getLottos());
        WinningLotto winningLotto = inputWinningLotto();
        displayTotalResultOfLottoGames(earlylMoney, lottos, winningLotto);
    }

    private WinningLotto inputWinningLotto() {
        Lotto inputtedWinningLottoNumbers = Lotto.createLottoNumbers(input.inputWiningLotto());
        LottoNumber inputtedBonusNumber = LottoNumber.valueOf(input.inputBonusNumber());
        return new WinningLotto(inputtedWinningLottoNumbers, inputtedBonusNumber);
    }

    private void displayTotalResultOfLottoGames(final Money earlylMoney, final Lottos lottos, final WinningLotto winningLotto) {
        output.displayLottoTotalResult(lottos.countLottoRanks(winningLotto));
        output.displayRateOfProfit(earlylMoney.calculateRateOfProfit(lottos.getToTalProfit()));
    }
}
