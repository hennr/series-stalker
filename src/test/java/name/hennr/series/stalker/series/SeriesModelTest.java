package name.hennr.series.stalker.series;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SeriesModelTest {

    @Test
    public void compareReturnsZeroOnSameDate() {
        // given
        SeriesModel foo = new SeriesModel();
        foo.nextAirDate = LocalDate.parse("2018-11-11");

        SeriesModel bar = new SeriesModel();
        bar.nextAirDate = LocalDate.parse("2018-11-11");

        // expect
        assertThat(foo.compareTo(bar)).isEqualTo(0);
    }

    @Test
    public void compareWorksProperlyForEventsFromThePast() {
        // given
        SeriesModel foo = new SeriesModel();
        foo.nextAirDate = LocalDate.parse("2017-11-11");

        SeriesModel bar = new SeriesModel();
        bar.nextAirDate = LocalDate.parse("2016-11-11");

        // expect
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(foo.compareTo(bar)).isNegative();
        softly.assertThat(bar.compareTo(foo)).isPositive();
        softly.assertAll();
    }

    @Test
    public void compareWorksProperlyForFutureDates() {
        // given
        SeriesModel twelveMonthsAhead = new SeriesModel();
        twelveMonthsAhead.nextAirDate = LocalDate.now().plus(Period.ofMonths(12));

        SeriesModel thirteenMonthsAhead = new SeriesModel();
        thirteenMonthsAhead.nextAirDate = LocalDate.now().plus(Period.ofMonths(13));

        // expect
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(twelveMonthsAhead.compareTo(thirteenMonthsAhead)).isNegative();
        softly.assertThat(thirteenMonthsAhead.compareTo(twelveMonthsAhead)).isPositive();
        softly.assertAll();
    }

    @Test
    public void compareWorksProperlyForFutureAndPastDates() {
        // given
        SeriesModel oneMonthInThePast = new SeriesModel();
        oneMonthInThePast.nextAirDate = LocalDate.now().minus(Period.ofMonths(1));

        SeriesModel oneMonthInTheFuture = new SeriesModel();
        oneMonthInTheFuture.nextAirDate = LocalDate.now().plus(Period.ofMonths(1));

        // expect
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(oneMonthInThePast.compareTo(oneMonthInTheFuture)).isPositive();
        softly.assertThat(oneMonthInTheFuture.compareTo(oneMonthInThePast)).isNegative();
        softly.assertAll();
    }
}
