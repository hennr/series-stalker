package name.hennr.series.stalker.tvmaze;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Mono;

@Service
public class EpisodeClient {

    @Autowired
    private RestTemplate restTemplate;

    public Mono<Pair<String, LocalDate>> getNextAirDateForSeriesWithId(String id) {
        String url = "http://api.tvmaze.com/shows/" + id + "/episodes";
        List<Episode> episodes = Arrays.asList(restTemplate.getForObject(url, Episode[].class));

        LocalDate latestKnownEpisode = LocalDate.of(1666, 1, 1);

        for (Episode episode : episodes) {
            LocalDate airdate;
            try {
                airdate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(episode.airdate));
                if (airdate.isAfter(LocalDate.now())) {
                    return Mono.just(Pair.of(id, airdate));
                } else {
                    latestKnownEpisode = airdate;
                }
            } catch (Exception e) {
                break;
            }
        }

        return Mono.just(Pair.of(id, latestKnownEpisode));
    }
}
