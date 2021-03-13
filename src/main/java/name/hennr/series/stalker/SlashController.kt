package name.hennr.series.stalker

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.result.view.RedirectView

@Controller
class SlashController {
    @GetMapping("/")
    fun redirectWithUsingRedirectView(): RedirectView {
        return RedirectView("/series.html")
    }
}
