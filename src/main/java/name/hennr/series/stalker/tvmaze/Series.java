package name.hennr.series.stalker.tvmaze;

public class Series {

    public Image image;

    public class Image {
        public String medium;
        public String original;

        public void setMedium(String url) {
            if (url.startsWith("http:")) {
                medium = url.replace("http:", "https:");
            }
        }

        public void setOriginal(String url) {
            if (url.startsWith("http:")) {
                original = url.replace("http:", "https:");
            }
        }
    }
}
