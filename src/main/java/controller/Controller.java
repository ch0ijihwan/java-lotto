package controller;

import model.LotteryResult;
import model.LottoMachine;
import model.dto.LottoDto;
import model.factory.LottoFactory;
import model.lotto.WinningLotto;
import model.lotto.vo.LottoNumber;
import model.purchase.CountOfManualPurchase;
import view.display.Display;
import view.input.Input;

import java.util.List;

public class Controller {
    private final Input input;
    private final Display display;

    public Controller(final Input input, final Display display) {
        this.input = input;
        this.display = display;
    }

    public void run() {
        LottoMachine lottoMachine = createVendingMachine();
        displayPurchasedLotteryTicket(lottoMachine);
        WinningLotto winningLotto = new WinningLotto(LottoFactory.createManualLotto(input.inputWiningLotto()), LottoNumber.valueOf(input.inputBonusNumber()));
        displayLotteryResult(lottoMachine, winningLotto);
    }

    private LottoMachine createVendingMachine() {
        int totalPurchaseAmount = input.inputTotalPurchaseAmount();
        CountOfManualPurchase countOfManualPurchase = new CountOfManualPurchase(input.inputCountOfManualPurchase(), totalPurchaseAmount);
        int countOfManualLottoInputs = countOfManualPurchase.getValue();
        List<LottoDto> informationManualLottos = input.inputManualLottoNumbers(countOfManualLottoInputs);
        return new LottoMachine(totalPurchaseAmount, informationManualLottos);
    }

    private void displayPurchasedLotteryTicket(final LottoMachine lottoMachine) {
        display.displayPurchaseCount(lottoMachine.getCountOfAutoPurchase(), lottoMachine.getCountOfManualPurchase());
        display.displayLotteryTicket(lottoMachine.getInformationOfLottos());
    }

    private void displayLotteryResult(final LottoMachine lottoMachine, final WinningLotto winningLotto) {
        LotteryResult lotteryResult = lottoMachine.getLotteryResult(winningLotto);
        display.displayLotteryResult(lotteryResult.getRankAndFrequencyNumber());
        display.displayRateOfProfit(lotteryResult.getRateOfProfit());
    }
}
