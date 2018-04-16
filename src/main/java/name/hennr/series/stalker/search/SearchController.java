package name.hennr.series.stalker.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @RequestMapping("/search")
    public String listSeries() {
        return "search";
    }
}
