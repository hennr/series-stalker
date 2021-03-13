package name.hennr.series.stalker.tvmaze

class Episode {
    var id = 0
    @kotlin.jvm.JvmField
    var airdate: String? = null

    // many fields are omitted here
    override fun toString(): String {
        return "Episode{" +
                "id=" + id +
                ", airdate='" + airdate + '\'' +
                '}'
    }
}
