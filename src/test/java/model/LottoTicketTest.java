package model;

import model.vo.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTicketTest {

    @Test
    @DisplayName("입력 받은 수동 로또와 자동 로또 갯수로만든 lottos 를 반환한다.")
    void createLottos() {
        //given
        int countOfAutoLotto = 11;
        List<Lotto> manualLotto = List.of(Lotto.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                Lotto.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)));

        LottoTicket lottoTicket = new LottoTicket(countOfAutoLotto, manualLotto);

        //when
        Lottos actual = lottoTicket.getLottos();

        //then
        assertThat(actual.getLottos().size()).isEqualTo(13);
    }
}
