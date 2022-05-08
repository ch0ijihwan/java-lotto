package model;

import model.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LottoNumbersVendingMachineTest {

    private LottoVendingMachine lottoVendingMachine;

    @BeforeEach
    void setUp() {
        Money money = new Money(14000);
        int countOfManualLotto = 3;
        lottoVendingMachine = new LottoVendingMachine(money, countOfManualLotto);
    }

    @Test
    @DisplayName("수동 구매 로또 수를 반환한다.")
    void getCountOfManualLotto() {
        //given
        int expect = 3;

        //when
        int actual = lottoVendingMachine.getCountOfManualLotto();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("자동 구매 로또 수를 반환한다.")
    void getCountOfAutoLotto() {
        //given
        int expect = 11;

        //when
        int actual = lottoVendingMachine.getCountOfAutoLotto();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("입력 받은 금액이 1000의 배수가 아니면 예외처리를 반환한다.")
    void validateInputtedMoney() {
        //given
        Money money = new Money(700);
        int countOfManualLotto = 3;

        //then
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoVendingMachine(money, countOfManualLotto))
                .withMessage("입력 금액은 1000원의 배수 단위어야 로또를 구매할 수 있습니다.");
    }
}