package name.hennr.series.stalker.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SeriesController {

    @Autowired
    EpisodeService episodeService;

    @GetMapping("/series")
    public ModelAndView listSeries() {
        ModelAndView modelAndView = new ModelAndView("series");
        modelAndView.addObject("foo", episodeService.getNextAirDateForTheWalkingDead());
        return modelAndView;
    }
}
