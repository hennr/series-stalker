package name.hennr.series.stalker.series;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class SeriesController {

    @Autowired
    SeriesService seriesService;

    @GetMapping(value = "/series/data")
    public Mono<List<SeriesModel>> seriesList() {
        return seriesService.getAllSeries();
    }
}
