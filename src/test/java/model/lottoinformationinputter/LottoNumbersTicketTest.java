package model.lottoinformationinputter;

import model.Lottos;
import model.lottoinformationinputter.LottoTicket;
import model.vo.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumbersTicketTest {

    @Test
    @DisplayName("입력 받은 수동 로또와 자동 로또 갯수로만든 lottos 를 반환한다.")
    void createLottos() {
        //given
        int countOfAutoLotto = 11;
        List<LottoNumbers> manualLottoNumbers = List.of(LottoNumbers.createManualLottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                LottoNumbers.createManualLottoNumbers(List.of(7, 8, 9, 10, 11, 12)));

        LottoTicket lottoTicket = new LottoTicket(countOfAutoLotto, manualLottoNumbers);

        //when
        Lottos actual = lottoTicket.getLottos();

        //then
        assertThat(actual.getLottos().size()).isEqualTo(13);
    }
}
