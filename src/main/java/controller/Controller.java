package controller;

import model.LotteryGame;
import model.LotteryResult;
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
        LotteryGame lotteryGame = createVendingMachine();
        displayPurchasedLotteryTicket(lotteryGame);
        WinningLotto winningLotto = new WinningLotto(LottoFactory.createManualLotto(input.inputWiningLotto()), LottoNumber.valueOf(input.inputBonusNumber()));
        displayLotteryResult(lotteryGame, winningLotto);
    }

    private LotteryGame createVendingMachine() {
        int totalPurchaseAmount = input.inputTotalPurchaseAmount();
        CountOfManualPurchase countOfManualPurchase = new CountOfManualPurchase(input.inputCountOfManualPurchase(), totalPurchaseAmount);
        int countOfManualLottoInputs = countOfManualPurchase.getValue();
        List<LottoDto> informationManualLottos = input.inputManualLottoNumbers(countOfManualLottoInputs);
        return new LotteryGame(totalPurchaseAmount, informationManualLottos);
    }

    private void displayPurchasedLotteryTicket(final LotteryGame lotteryGame) {
        display.displayPurchaseCount(lotteryGame.getCountOfAutoPurchase(), lotteryGame.getCountOfManualPurchase());
        display.displayLotteryTicket(lotteryGame.getInformationOfLottos());
    }

    private void displayLotteryResult(final LotteryGame lotteryGame, final WinningLotto winningLotto) {
        LotteryResult lotteryResult = lotteryGame.getLotteryResult(winningLotto);
        display.displayLotteryResult(lotteryResult.getRankAndFrequencyNumber());
        display.displayRateOfProfit(lotteryResult.getRateOfProfit());
    }
}
