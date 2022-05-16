package view.input;

import controller.dto.LottoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInput implements Input {

    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String INPUT_TOTAL_PURCHASE_AMOUNT_MESSAGE = "구입 금액을 입력해주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼 넘버를 입력해주세요. ";
    private static final String INPUT_COUNT_OF_MANUAL_PURCHASE_MESSAGE = "수동으로 구매할 로또 수를 입력해주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "수동으로 구매할 번호를 입력해 주세요";
    private static final String INPUT_WINNING_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final Scanner SCANNER = new Scanner(System.in);

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
        List<String> informationNumbers = new ArrayList<>();
        for (int i = 0; i < countOfManualPurchase; i++) {
            SCANNER.nextLine();
            informationNumbers.add(SCANNER.nextLine());
        }
        return convertToLottoDto(informationNumbers);
    }

    private List<LottoDto> convertToLottoDto(final List<String> informationNumbers) {
        return informationNumbers.stream()
                .map(this::convertToSplitInteger)
                .map(LottoDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> convertToSplitInteger(final String numbers) {
        return Arrays.stream(splitNumbers(numbers))
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
    }

    private String[] splitNumbers(final String numbers) {
        return numbers.strip()
                .split(LOTTO_NUMBER_DELIMITER);
    }


    @Override
    public List<Integer> inputWiningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_NUMBERS);
        return convertToSplitInteger(SCANNER.nextLine());
    }

    @Override
    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return SCANNER.nextInt();
    }
}
