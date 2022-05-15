package lotto.domain;

import lotto.constants.DisplayMessage;
import lotto.enums.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoWinningRanksTest {
    private LottoWinningRanks lottoWinningRanks;

    @BeforeEach
    void setUp() {
        //given
        LottoTicket winningNumbers = LottoTicket.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.from(7);

        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 7, 8, 9);
        LottoTicket userNumbers = LottoTicket.from(numbers1);
        lottoWinningRanks = LottoWinningRanks.create();
        lottoWinningRanks.addWinningRank(userNumbers.rank(winningNumbers, bonusBall));

        List<Integer> numbers2 = Arrays.asList(4, 5, 6, 10, 11, 12);
        userNumbers = LottoTicket.from(numbers2);
        lottoWinningRanks.addWinningRank(userNumbers.rank(winningNumbers, bonusBall));

        List<Integer> missNumbers = Arrays.asList(7, 8, 9, 10, 11, 12);
        userNumbers = LottoTicket.from(missNumbers);
        lottoWinningRanks.addWinningRank(userNumbers.rank(winningNumbers, bonusBall));
    }

    @DisplayName("당첨된 로또 순위 추가")
    @Test
    void test_로또_순위_추가() {
        //when & then
        assertThat(lottoWinningRanks.size()).isEqualTo(2);
    }

    @DisplayName("로또 순위 개수 조회")
    @Test
    void test_순위_개수() {
        //when & then
        assertThat(lottoWinningRanks.rankCount(Rank.FIFTH)).isEqualTo(2);
    }

    @DisplayName("로또 수익률 조회")
    @Test
    void test_로또_수익률() {
        //given
        Money purchaseMoney = Money.from(14000);
        //when & then
        assertThat(lottoWinningRanks.returnRate(purchaseMoney)).isEqualTo(0.71d);
    }

    @DisplayName("수익 결과 손해")
    @Test
    void test_수익_결과_손해() {
        //given
        Money purchaseMoney = Money.from(14000);
        //when & then
        assertThat(lottoWinningRanks.resultDescription(purchaseMoney))
                .isEqualTo(DisplayMessage.LOSS);
    }

    @DisplayName("수익 결과 원금")
    @Test
    void test_수익_결과_원금() {
        //given
        Money purchaseMoney = Money.from(10000);
        //when & then
        assertThat(lottoWinningRanks.resultDescription(purchaseMoney))
                .isEqualTo(DisplayMessage.PRINCIPAL_AND_POST_POSITION);
    }

    @DisplayName("수익 결과 이득")
    @Test
    void test_수익_결과_이득() {
        //given
        Money purchaseMoney = Money.from(5000);
        //when & then
        assertThat(lottoWinningRanks.resultDescription(purchaseMoney))
                .isEqualTo(DisplayMessage.GAIN_AND_POST_POSITION);
    }
}