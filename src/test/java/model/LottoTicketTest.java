package model;

import model.vo.Lotto;
import model.vo.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTicketTest {

    private LottoTicket lottoTicket;

    @BeforeEach
    void setUp() {
        Money money = new Money(10000);
        int numberOfManualLotto = 3;
        List<List<Integer>> manualLottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(7, 8, 9, 10, 11, 12)
        );
        lottoTicket = new LottoTicket(money, numberOfManualLotto, manualLottoNumbers);
    }

    @Test
    @DisplayName("자동 로또 갯수를 반환한다.")
    void getNumberOfAutoLotto() {
        //given
        int expect = 7;

        //when
        int actual = lottoTicket.getNumberOfAutoLotto();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("수동 로또 갯수를 반환한다.")
    void getNumberOfManualLotto() {
        //given
        int expect = 3;

        //when
        int actual = lottoTicket.getNumberOfManualLotto();

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("수동으로 구매할 번호들을 입력 받아 수동 로또 번호들을 반환한다.")
    void createManualLottos() {
        //given
        List<Lotto> expect = List.of(Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)));
        //when
        List<Lotto> actual = lottoTicket.getManualLottos();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}