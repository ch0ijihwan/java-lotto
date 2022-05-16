package controller;

import model.LotteryGame;
import model.LotteryResult;
import model.dto.LottoDto;
import model.factory.LottoFactory;
import model.lotto.LastWinningLotto;
import model.lotto.vo.Lotto;
import model.lotto.vo.LottoNumber;
import model.purchase.CountOfManualPurchase;
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
        displayPurchasedLotteryTicket(lotteryGame);
        LastWinningLotto lastWinningLotto = new LastWinningLotto(LottoFactory.createManualLotto(input.inputWiningLotto()),
                LottoNumber.valueOf(input.inputBonusNumber()));
        displayLotteryResult(lotteryGame, lastWinningLotto);
    }

    private LotteryGame createLotteryGame() {
        int totalPurchaseAmount = input.inputTotalPurchaseAmount();
        CountOfManualPurchase countOfManualPurchase = new CountOfManualPurchase(input.inputCountOfManualPurchase(), totalPurchaseAmount);
        int countOfManualLottoInputs = countOfManualPurchase.getCountOfManualPurchase();
        List<LottoDto> informationOfManualLottos = input.inputManualLottoNumbers(countOfManualLottoInputs);
        return new LotteryGame(totalPurchaseAmount, informationOfManualLottos);
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
        display.displayRateOfProfit(lotteryResult.getRateOfProfit());
    }
}
