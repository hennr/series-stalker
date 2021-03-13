package name.hennr.series.stalker.series

import name.hennr.series.stalker.tvmaze.EpisodeClient
import name.hennr.series.stalker.tvmaze.SeriesClient
import name.hennr.series.stalker.tvmaze.SeriesImages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.util.function.Function
import java.util.stream.Collectors

@Service
class SeriesService() {
    @Autowired
    private val episodeClient: EpisodeClient? = null

    @Autowired
    private val seriesClient: SeriesClient? = null

    @Value("#{'\${series.ids}'.split(',')}")
    private val seriesIds: List<String>? = null
    val allSeries: Mono<List<SeriesModel>>
        get() {
            val imagesFlux: Flux<Pair<String, SeriesImages>> = Flux.merge(
                seriesIds!!.stream().map(
                    { id: String? -> seriesClient!!.getImageUrlForSeriesWithId(id!!) }).collect(Collectors.toList())
            )
            val nextAirDateFlux: Flux<Pair<String, LocalDate>> = Flux.merge(
                seriesIds.stream().map(
                    { id: String? -> episodeClient!!.getNextAirDateForSeriesWithId(id!!) }).collect(Collectors.toList())
            )
            val result: Mono<List<SeriesModel>> = Mono.zip(imagesFlux.collectMap(
                { obj: Pair<String, SeriesImages> -> obj.getFirst() },
                { obj: Pair<String, SeriesImages> -> obj.getSecond() }),
                nextAirDateFlux.collectMap(
                    { obj: Pair<String, LocalDate> -> obj.getFirst() },
                    { obj: Pair<String, LocalDate> -> obj.getSecond() }),
                { images: Map<String, SeriesImages>, dates: Map<String, LocalDate> ->
                    seriesIds.stream().map(
                        Function { id: String -> SeriesModel(images.get(id)!!.image?.original, dates.get(id)) }).sorted(
                        java.util.Comparator { obj: SeriesModel, seriesModel: SeriesModel? -> obj.compareTo((seriesModel)!!) })
                        .collect(Collectors.toList())
                })
            return result
        }
}
