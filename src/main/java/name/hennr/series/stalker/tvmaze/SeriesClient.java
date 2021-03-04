package name.hennr.series.stalker.tvmaze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Service
public class SeriesClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getImageUrlForSeriesWithId(String id) {
        return new HystrixSeriesClient(id).execute();
    }

    private class HystrixSeriesClient extends HystrixCommand<String> {

        private String id;

        HystrixSeriesClient(String id) {
            super(HystrixCommandGroupKey.Factory.asKey("series"), 1500);
            this.id = id;
        }

        @Override
        protected String run() {
            String url = "https://api.tvmaze.com/shows/" + id;
            Series series = restTemplate.getForObject(url, Series.class);
            return series.image.original;
        }

        @Override
        protected String getFallback() {
            return "";
        }
    }
}
