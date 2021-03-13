package name.hennr.series.stalker.tvmaze;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class SeriesClient {

    private WebClient webClient = WebClient.create("https://api.tvmaze.com/shows/");

    public Mono<Pair<String, SeriesImages>> getImageUrlForSeriesWithId(String id) {
        return webClient
            .get()
            .uri(id)
            .retrieve()
            .bodyToMono(SeriesImages.class)
                .map(seriesImages -> Pair.of(id, seriesImages));
    }
}
