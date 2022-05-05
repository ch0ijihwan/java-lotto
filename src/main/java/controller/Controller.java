package controller;

import model.Lottos;
import model.vo.Money;
import model.WinningLotto;
import model.vo.Lotto;
import model.vo.LottoNumber;
import model.vo.Profit;
import model.vo.Rank;
import view.input.Input;
import view.output.Display;

import java.util.Map;

public class Controller {

    private final Input input;
    private final Display output;

    public Controller(final Input input, final Display display) {
        this.input = input;
        this.output = display;
    }

    public void run() {
        Money initialMoney = new Money(input.inputMoney());
        Lottos lottos = new Lottos(initialMoney.getChanceToBuyLotto());
        output.displayDetailsOfLottoPurchased(lottos.getLottos());
        WinningLotto winningLotto = inputWinningLotto();
        displayTotalResultOfLottoGames(initialMoney, lottos, winningLotto);
    }

    private WinningLotto inputWinningLotto() {
        Lotto inputtedWinningLottoNumbers = Lotto.createManualLottoNumbers(input.inputWiningLotto());
        LottoNumber inputtedBonusNumber = LottoNumber.valueOf(input.inputBonusNumber());
        return new WinningLotto(inputtedWinningLottoNumbers, inputtedBonusNumber);
    }

    private void displayTotalResultOfLottoGames(final Money money, final Lottos lottos, final WinningLotto winningLotto) {
        Map<Rank, Integer> resultTotalRanks = lottos.countLottoRanks(winningLotto);
        output.displayLottoTotalResult(resultTotalRanks);
        Profit profit = new Profit(money, resultTotalRanks);
        output.displayRateOfProfit(profit.getRateOfProfit());
    }
}
