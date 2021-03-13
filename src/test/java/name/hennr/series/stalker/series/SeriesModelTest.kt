package name.hennr.series.stalker.series

import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Period

class SeriesModelTest {
    @Test
    fun `compare returns zero on same date`() {
        // given
        val foo = SeriesModel()
        foo.nextAirDate = LocalDate.parse("2018-11-11")
        val bar = SeriesModel()
        bar.nextAirDate = LocalDate.parse("2018-11-11")

        // expect
        AssertionsForClassTypes.assertThat(foo.compareTo(bar)).isEqualTo(0)
    }

    @Test
    fun `compare works properly for events from the past`() {
        // given
        val foo = SeriesModel()
        foo.nextAirDate = LocalDate.parse("2017-11-11")
        val bar = SeriesModel()
        bar.nextAirDate = LocalDate.parse("2016-11-11")

        // expect
        val softly = SoftAssertions()
        softly.assertThat(foo.compareTo(bar)).isNegative
        softly.assertThat(bar.compareTo(foo)).isPositive
        softly.assertAll()
    }

    @Test
    fun `compare works properly for future dates`() {
        // given
        val twelveMonthsAhead = SeriesModel()
        twelveMonthsAhead.nextAirDate = LocalDate.now().plus(Period.ofMonths(12))
        val thirteenMonthsAhead = SeriesModel()
        thirteenMonthsAhead.nextAirDate = LocalDate.now().plus(Period.ofMonths(13))

        // expect
        val softly = SoftAssertions()
        softly.assertThat(twelveMonthsAhead.compareTo(thirteenMonthsAhead)).isNegative
        softly.assertThat(thirteenMonthsAhead.compareTo(twelveMonthsAhead)).isPositive
        softly.assertAll()
    }

    @Test
    fun `compare works properly for future and past dates`() {
        // given
        val oneMonthInThePast = SeriesModel()
        oneMonthInThePast.nextAirDate = LocalDate.now().minus(Period.ofMonths(1))
        val oneMonthInTheFuture = SeriesModel()
        oneMonthInTheFuture.nextAirDate = LocalDate.now().plus(Period.ofMonths(1))

        // expect
        val softly = SoftAssertions()
        softly.assertThat(oneMonthInThePast.compareTo(oneMonthInTheFuture)).isPositive
        softly.assertThat(oneMonthInTheFuture.compareTo(oneMonthInThePast)).isNegative
        softly.assertAll()
    }
}
