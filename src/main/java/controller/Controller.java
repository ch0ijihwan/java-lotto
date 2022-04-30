package controller;

import model.*;
import model.lotto.Lotto;
import model.lotto.Lottos;
import model.lotto.WinningLotto;
import model.vo.LottoNumber;
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
        Money initialMoney = new Money(input.inputMoney());
        Lottos lottos = new Lottos(initialMoney);
        output.displayDetailsOfLottoPurchased(lottos.getLottos());
        WinningLotto winningLotto = inputWinningLotto();
        displayTotalResultOfLottoGames(initialMoney, lottos, winningLotto);
    }

    private WinningLotto inputWinningLotto() {
        Lotto inputtedWinningLottoNumbers = Lotto.createLottoNumbers(input.inputWiningLotto());
        LottoNumber inputtedBonusNumber = LottoNumber.valueOf(input.inputBonusNumber());
        return new WinningLotto(inputtedWinningLottoNumbers, inputtedBonusNumber);
    }

    private void displayTotalResultOfLottoGames(final Money initialMoney, final Lottos lottos, final WinningLotto winningLotto) {
        output.displayLottoTotalResult(lottos.countLottoRanks(winningLotto));
        output.displayRateOfProfit(initialMoney.calculateRateOfProfit(lottos.getToTalProfit()));
    }
}
