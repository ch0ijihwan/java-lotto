package view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInput implements Input {
    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해주세요 %n";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼 넘버를 입력해주세요. %n";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.%n";
    private static final String INPUT_COUNT_OF_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요";
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public int inputMoney() {
        System.out.printf(INPUT_MONEY_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public int inputBonusNumber() {
        System.out.printf(INPUT_BONUS_NUMBER_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public int inputCountOfManualLotto() {
        System.out.println(INPUT_COUNT_OF_MANUAL_LOTTO_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public List<Integer> inputManualLottoNumbers() {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        return Arrays.stream(SCANNER.next().split(LOTTO_NUMBER_DELIMITER))
                .map(Integer::valueOf)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Integer> inputWiningLotto() {
        System.out.printf(INPUT_WINNING_LOTTO_NUMBERS);
        String[] winingLottoNumbers = SCANNER.next()
                .split(LOTTO_NUMBER_DELIMITER);
        return Arrays.stream(winingLottoNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }
}
