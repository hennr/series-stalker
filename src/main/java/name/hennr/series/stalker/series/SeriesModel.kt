package name.hennr.series.stalker.series

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SeriesModel : Comparable<SeriesModel> {
    var imageUrl: String? = null

    constructor() {}
    constructor(imageUrl: String?, nextAirDate: LocalDate?) {
        this.imageUrl = imageUrl
        this.nextAirDate = nextAirDate
    }

    @JvmField
    var nextAirDate: LocalDate? = null
    fun getNextAirDate(): String {
        return if (nextAirDate!!.isBefore(LocalDate.now())) {
            "last: " + nextAirDate!!.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
        } else {
            "next: " + nextAirDate!!.format(DateTimeFormatter.ofPattern("dd.MM.yy"))
        }
    }

    override fun compareTo(seriesModel: SeriesModel): Int {
        return if (seriesModel.nextAirDate!!.isBefore(LocalDate.now()) && nextAirDate!!.isBefore(LocalDate.now())) {
            seriesModel.nextAirDate!!.compareTo(nextAirDate)
        } else if (seriesModel.nextAirDate!!.isAfter(LocalDate.now()) && nextAirDate!!.isAfter(LocalDate.now())) {
            -1 * seriesModel.nextAirDate!!.compareTo(nextAirDate)
        } else {
            seriesModel.nextAirDate!!.compareTo(nextAirDate)
        }
    }
}
