package name.hennr.series.stalker.series;

public class Episode {

    public int id;
    public String url;
    public String name;
    public int season;
    public int number;
    public String airdate;
    public String airtime;
    public String airstamp;
    public int runtime;

    // some fields are omitted here

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", season=" + season +
                ", number=" + number +
                ", airdate='" + airdate + '\'' +
                ", airtime='" + airtime + '\'' +
                ", airstamp='" + airstamp + '\'' +
                ", runtime=" + runtime +
                '}';
    }
}
