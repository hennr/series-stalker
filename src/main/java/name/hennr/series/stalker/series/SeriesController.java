package name.hennr.series.stalker.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesController {

    @Autowired
    SeriesService seriesService;

    @GetMapping(value = "/series/data")
    public Object seriesList() {
        return seriesService.getAllSeries();
    }
}
