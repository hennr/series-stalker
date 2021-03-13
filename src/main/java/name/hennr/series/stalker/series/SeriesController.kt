package name.hennr.series.stalker.series

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SeriesController(val seriesService: SeriesService) {

    @GetMapping(value = ["/series/data"])
    fun seriesList(): Mono<List<SeriesModel>> {
        return seriesService.allSeries()
    }

}
