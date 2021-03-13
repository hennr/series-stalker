package name.hennr.series.stalker.series

import name.hennr.series.stalker.tvmaze.EpisodeClient
import name.hennr.series.stalker.tvmaze.SeriesClient
import name.hennr.series.stalker.tvmaze.SeriesImages
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
class SeriesService(val episodeClient: EpisodeClient,
                    val seriesClient: SeriesClient,
                    @Value("#{'\${series.ids}'.split(',')}") private val seriesIds: List<String>) {

    fun allSeries(): Mono<List<SeriesModel>> {
        val imagesFlux: Flux<Pair<String, SeriesImages>> = Flux.merge(
                seriesIds.map { id: String? -> seriesClient.getImageUrlForSeriesWithId(id!!) }
        )

        val nextAirDateFlux: Flux<Pair<String, LocalDate>> = Flux.merge(
                seriesIds.map { id: String? -> episodeClient.getNextAirDateForSeriesWithId(id!!) }
        )

        return Mono.zip(
                imagesFlux.collectMap({ obj: Pair<String, SeriesImages> -> obj.first }, { obj: Pair<String, SeriesImages> -> obj.second }),
                nextAirDateFlux.collectMap({ obj: Pair<String, LocalDate> -> obj.first }, { obj: Pair<String, LocalDate> -> obj.second })
        ) { images, dates ->
            seriesIds.map { id: String ->
                SeriesModel(images[id]!!.image?.original, dates[id])
            }.sorted()
        }
    }
}
