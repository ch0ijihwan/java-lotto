package model.lottoinformationinputter;

import model.vo.LottoNumbersFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ManualLottoNumbersFactoryInjectorTest {
    private ManualLottoInjector manualLottoInjector;

    @BeforeEach
    void setUp() {
        int manualCount = 4;
        manualLottoInjector = new ManualLottoInjector(manualCount);
    }

    @Test
    @DisplayName("수동으로 입력 받을 수 있는 남은 횟수를 반환한다.")
    void getManualRemainingCount() {
        //given
        int expect = 4;

        //when
        int actual = manualLottoInjector.getManualRemainingCount();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("수동 로또 넘버들을 받아 추가한다.")
    void addManualLottoNumbers() {
        //given
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6);
        List<LottoNumbersFactory> expect = List.of(LottoNumbersFactory.createManualLottoNumbers(input));

        //when
        manualLottoInjector.add(input);

        //then
        assertThat(manualLottoInjector).hasFieldOrPropertyWithValue("manualLottos", expect);
    }


    @Test
    @DisplayName("로또들을 반환한다.")
    void getManualLottos() {
        //given
        List<Integer> first = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> second = List.of(7, 8, 9, 10, 11, 12);
        manualLottoInjector.add(first);
        manualLottoInjector.add(second);
        List<LottoNumbersFactory> expect = List.of(LottoNumbersFactory.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                LottoNumbersFactory.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)));

        //when
        List<LottoNumbersFactory> actual = manualLottoInjector.getManualLottos();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}