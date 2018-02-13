package name.hennr.series.stalker.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping("/series")
    public String listSeries(final Model model) {
        model.addAttribute("seriesList", seriesService.getAllSeries());
        return "series";
    }
}
