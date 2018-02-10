package name.hennr.series.stalker.tvmaze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SeriesClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getImageUrlForSeriesWithId(String id) {
        String url = "http://api.tvmaze.com/shows/" + id;
        Series series = restTemplate.getForObject(url, Series.class);
        return series.image.original;
    }
}
