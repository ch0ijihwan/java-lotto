package view.input;

import model.dto.LottoDto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInput implements Input {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String INPUT_TOTAL_PURCHASE_AMOUNT_MESSAGE = "구입 금액을 입력해주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼 넘버를 입력해주세요. ";
    private static final String INPUT_COUNT_OF_MANUAL_PURCHASE_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";


    @Override
    public int inputTotalPurchaseAmount() {
        System.out.println(INPUT_TOTAL_PURCHASE_AMOUNT_MESSAGE);
        return SCANNER.nextInt();
    }


    @Override
    public int inputCountOfManualPurchase() {
        System.out.println(INPUT_COUNT_OF_MANUAL_PURCHASE_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public List<LottoDto> inputManualLottoNumbers(final int countOfManualPurchase) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        return Stream.generate(this::inputLotteryNumbers)
                .limit(countOfManualPurchase)
                .map(LottoDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> inputLotteryNumbers() {
        return Arrays.stream(splitLotteryNumbers())
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }

    private String[] splitLotteryNumbers() {
        return SCANNER.nextLine()
                .split(ConsoleInput.LOTTO_NUMBER_DELIMITER);
    }

    @Override
    public List<Integer> inputWiningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
        return inputLotteryNumbers();
    }

    @Override
    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return SCANNER.nextInt();
    }
}
