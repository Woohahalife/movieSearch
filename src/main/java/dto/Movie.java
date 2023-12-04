package dto;

public class Movie {

    private int index;
    private String title;
    private String major;
    private String runningTime;
    private double rating;
    private Genre genre;

    public Movie( String title, String major, String runningTime, double rating, Genre genre) {
        this.title = title;
        this.major = major;
        this.runningTime = runningTime;
        this.rating = rating;
        this.genre = genre;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getMajor() {
        return major;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public double getRating() {
        return rating;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "\n영화"+ (index) +": '" + title + '\'' +
                ", 주연 : '" + major + '\'' +
                ", 러닝타임 : " + runningTime +
                ", 평점 : " + rating +
                ", 장르 : " + genre +
                "";
    }
}
