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


    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public int inputMoney() {
        System.out.print(INPUT_MONEY_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public int inputBonusNumber() {
        System.out.print(INPUT_BONUS_NUMBER_MESSAGE);
        return SCANNER.nextInt();
    }

    @Override
    public List<Integer> inputWiningLotto() {
        System.out.print(INPUT_WINNING_LOTTO_NUMBERS);
        String[] winingLottoNumbers = SCANNER.next()
                .split(LOTTO_NUMBER_DELIMITER);
        return Arrays.stream(winingLottoNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }
}
