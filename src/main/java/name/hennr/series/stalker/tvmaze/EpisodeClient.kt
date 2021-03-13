package name.hennr.series.stalker.tvmaze

import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.util.Pair
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
class EpisodeClient {

    private val webClient = WebClient.create("https://api.tvmaze.com/shows/")

    fun getNextAirDateForSeriesWithId(seriesId: String): Mono<Pair<String, LocalDate>> {
        val episodesResponse: Mono<List<Episode>> = webClient
                .get()
                .uri("$seriesId/episodes")
                .retrieve()
                .bodyToMono(object : ParameterizedTypeReference<List<Episode>>() {})

        return episodesResponse.map { episodes: List<Episode> ->
            Pair.of(seriesId, calculateLatestRelevantEpisode(episodes))
        }
    }

    private fun calculateLatestRelevantEpisode(episodes: List<Episode>): LocalDate {
        val ascendingEpisodeListByDate: List<LocalDate> = episodes.sortedBy { it.airdate }.map { LocalDate.parse(it.airdate) }

        return if (ascendingEpisodeListByDate.last().isBefore(LocalDate.now())) {
            ascendingEpisodeListByDate.last()
        } else {
            ascendingEpisodeListByDate.first { it.isAfter(LocalDate.now()) || it == LocalDate.now() }
        }
    }
}
