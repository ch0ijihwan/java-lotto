package controller;

import model.*;
import model.lottoinformationinputter.LottoTicket;
import model.lottoinformationinputter.LottoVendingMachine;
import model.lottoinformationinputter.ManualLottoInjector;
import model.vo.*;
import view.input.Input;
import view.output.Display;

import java.util.List;
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
        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine(initialMoney, input.inputCountOfManualLotto());
        ManualLottoInjector manualLottoInjector = inputManualLottoNumbers(lottoVendingMachine.getCountOfManualLotto());
        Lottos lottos = processLottoGame(lottoVendingMachine, manualLottoInjector);
        processLottoResult(initialMoney, lottos);
    }

    private ManualLottoInjector inputManualLottoNumbers(final int countOfManualLotto) {
        ManualLottoInjector manualLottoInjector = new ManualLottoInjector(countOfManualLotto);
        while (manualLottoInjector.getManualRemainingCount() > 0) {
            manualLottoInjector.add(input.inputManualLottoNumbers());
        }
        return manualLottoInjector;
    }

    private Lottos processLottoGame(final LottoVendingMachine lottoVendingMachine, final ManualLottoInjector manualLottoInjector) {
        Lottos lottos = createLottos(lottoVendingMachine, manualLottoInjector);
        output.displayDetailsOfLottoPurchased(lottos.getLottos());
        return lottos;
    }

    private Lottos createLottos(final LottoVendingMachine lottoVendingMachine, final ManualLottoInjector manualLottoInjector) {
        int countOfAutoLotto = lottoVendingMachine.getCountOfAutoLotto();
        List<LottoNumbersFactory> manualLottoNumberFactories = manualLottoInjector.getManualLottos();
        return new LottoTicket(countOfAutoLotto, manualLottoNumberFactories).getLottos();
    }

    private void processLottoResult(final Money initialMoney, final Lottos lottos) {
        WinningLotto winningLotto = inputWinningLotto();
        displayTotalResultOfLottoGames(initialMoney, lottos, winningLotto);
    }


    private WinningLotto inputWinningLotto() {
        LottoNumbersFactory inputtedWinningLottoNumbersNumbersFactory = LottoNumbersFactory.createManualLottoNumbers(input.inputWiningLotto());
        LottoNumber inputtedBonusNumber = LottoNumber.valueOf(input.inputBonusNumber());
        return new WinningLotto(inputtedWinningLottoNumbersNumbersFactory, inputtedBonusNumber);
    }

    private void displayTotalResultOfLottoGames(final Money money, final Lottos lottos, final WinningLotto winningLotto) {
        Map<Rank, Integer> resultTotalRanks = lottos.countLottoRanks(winningLotto);
        output.displayLottoTotalResult(resultTotalRanks);
        Profit profit = new Profit(money, resultTotalRanks);
        output.displayRateOfProfit(profit.getRateOfProfit());
    }
}
