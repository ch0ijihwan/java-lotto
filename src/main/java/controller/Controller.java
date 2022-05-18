package controller;

import controller.dto.LottoDto;
import model.lotto.entity.LotteryGame;
import model.lotto.factory.LottoFactory;
import model.lotto.vo.LastWinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.purchase.vo.CountOfManualPurchase;
import model.result.vo.LotteryResult;
import view.display.Display;
import view.input.Input;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Input input;
    private final Display display;

    public Controller(final Input input, final Display display) {
        this.input = input;
        this.display = display;
    }

    public void run() {
        LotteryGame lotteryGame = createLotteryGame();
        inputManulLottos(lotteryGame);
        displayPurchasedLotteryTicket(lotteryGame);
        LastWinningLotto lastWinningLotto = new LastWinningLotto(LottoFactory.createManualLotto(input.inputWiningLotto()),
                LottoNumber.valueOf(input.inputBonusNumber()));
        displayLotteryResult(lotteryGame, lastWinningLotto);
    }

    private void inputManulLottos(LotteryGame lotteryGame) {
        display.displayManualNumbersMessage();
        while (lotteryGame.hasCountOfManualLottos()) {
            lotteryGame.addManualLotto(LottoFactory.createManualLotto(input.inputManualLottoNumbers2()));
        }
    }

    private LotteryGame createLotteryGame() {
        int totalPurchaseAmount = input.inputTotalPurchaseAmount();
        CountOfManualPurchase countOfManualPurchase = new CountOfManualPurchase(input.inputCountOfManualPurchase(), totalPurchaseAmount);
        int countOfManualLottoInputs = countOfManualPurchase.getCountOfManualPurchase();
        return new LotteryGame(totalPurchaseAmount, countOfManualLottoInputs);
    }

    private void displayPurchasedLotteryTicket(final LotteryGame lotteryGame) {
        display.displayPurchaseCount(lotteryGame.getCountOfAutoPurchase(), lotteryGame.getCountOfManualPurchase());
        List<LottoDto> lottoDtos = lotteryGame.getInformationOfLottos()
                .stream()
                .map(this::createLottoDto)
                .collect(Collectors.toUnmodifiableList());
        display.displayLotteryTicket(lottoDtos);
    }

    private LottoDto createLottoDto(final Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
        return new LottoDto(lottoNumbers);
    }

    private void displayLotteryResult(final LotteryGame lotteryGame, final LastWinningLotto lastWinningLotto) {
        LotteryResult lotteryResult = lotteryGame.getLotteryResult(lastWinningLotto);
        display.displayLotteryResult(lotteryResult.getRankAndFrequencyNumber());
        display.displayRateOfProfit(lotteryResult.calculateRateOfProfit());
    }
}
