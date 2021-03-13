package name.hennr.series.stalker.tvmaze

import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class EpisodeClient {

    private val webClient = WebClient.create("https://api.tvmaze.com/shows/")

    fun getNextAirDateForSeriesWithId(seriesId: String): Mono<Pair<String, LocalDate>> {
        val episodes: Mono<List<Episode>> = webClient
            .get()
            .uri("$seriesId/episodes")
            .retrieve()
            .bodyToMono(object: ParameterizedTypeReference<List<Episode>> () {})

        return episodes.map { episodes ->
            Pair.of(
                seriesId,
                LocalDate.from(DateTimeFormatter.ISO_DATE.parse(
                    episodes
//                    .filter { LocalDate.from(DateTimeFormatter.ISO_DATE.parse(it.airdate)).isAfter(LocalDate.now())}
                    .sortedBy { it.airdate }
                        .last()
                        .airdate))
            )
        }
    }
}
