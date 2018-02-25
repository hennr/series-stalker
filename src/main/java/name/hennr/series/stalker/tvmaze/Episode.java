package name.hennr.series.stalker.tvmaze;

public class Episode {

    public int id;
    public String airdate;

    // many fields are omitted here

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", airdate='" + airdate + '\'' +
                '}';
    }
}
