package name.hennr.series.stalker.search

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SearchController {
    @RequestMapping("/search")
    fun listSeries(): String {
        return "search"
    }
}
