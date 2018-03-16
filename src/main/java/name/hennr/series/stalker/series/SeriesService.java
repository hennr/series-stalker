package name.hennr.series.stalker.series;

import name.hennr.series.stalker.tvmaze.EpisodeClient;
import name.hennr.series.stalker.tvmaze.SeriesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {

    @Autowired
    private EpisodeClient episodeClient;

    @Autowired
    private SeriesClient seriesClient;

    @Value("#{'${series.ids}'.split(',')}")
    private List<String> seriesIds;

    public List<SeriesModel> getAllSeries() {

        List<SeriesModel> seriesModels = new ArrayList<>();

        for (String id : seriesIds) {
            SeriesModel model = new SeriesModel();
            model.nextAirDate = episodeClient.getNextAirDateForSeriesWithId(id);
            model.imageUrl = seriesClient.getImageUrlForSeriesWithId(id);
            seriesModels.add(model);
        }

        seriesModels.sort(SeriesModel::compareTo);

        return seriesModels;
    }
}
