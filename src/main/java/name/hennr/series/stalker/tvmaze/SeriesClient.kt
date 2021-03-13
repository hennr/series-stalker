package name.hennr.series.stalker.tvmaze

import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class SeriesClient {
    private val webClient = WebClient.create("https://api.tvmaze.com/shows/")
    fun getImageUrlForSeriesWithId(id: String): Mono<Pair<String, SeriesImages>> {
        return webClient
            .get()
            .uri(id)
            .retrieve()
            .bodyToMono(SeriesImages::class.java)
            .map { seriesImages: SeriesImages -> Pair.of(id, seriesImages) }
    }
}
