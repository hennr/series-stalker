package name.hennr.series.stalker.series;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import name.hennr.series.stalker.tvmaze.EpisodeClient;
import name.hennr.series.stalker.tvmaze.SeriesClient;
import name.hennr.series.stalker.tvmaze.SeriesImages;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SeriesService {

    @Autowired
    private EpisodeClient episodeClient;

    @Autowired
    private SeriesClient seriesClient;

    @Value("#{'${series.ids}'.split(',')}")
    private List<String> seriesIds;

    public Mono<List<SeriesModel>> getAllSeries() {

        Flux<Pair<String, SeriesImages>> imagesFlux = Flux.merge(seriesIds.stream().map(id -> seriesClient.getImageUrlForSeriesWithId(id)).collect(Collectors.toList()));
        Flux<Pair<String, LocalDate>> nextAirDateFlux = Flux.merge(seriesIds.stream().map(id -> episodeClient.getNextAirDateForSeriesWithId(id)).collect(Collectors.toList()));

        Mono<List<SeriesModel>> result = Mono.zip(imagesFlux.collectMap(Pair::getFirst, Pair::getSecond), nextAirDateFlux.collectMap(Pair::getFirst, Pair::getSecond), (images, dates) -> {
            return seriesIds.stream().map(id -> {
                return new SeriesModel(images.get(id).image.original, dates.get(id));
            }).sorted(SeriesModel::compareTo).collect(Collectors.toList());
        });

        return result;
    }
}
