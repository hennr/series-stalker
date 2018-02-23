package name.hennr.series.stalker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;

@Controller
public class SlashController {

    @GetMapping("/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/series");
    }
}
