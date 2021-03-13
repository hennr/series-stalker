package name.hennr.series.stalker.series

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class SeriesController {
    @Autowired
    var seriesService: SeriesService? = null
    @GetMapping(value = ["/series/data"])
    fun seriesList(): Mono<List<SeriesModel>> {
        return seriesService!!.allSeries
    }
}
