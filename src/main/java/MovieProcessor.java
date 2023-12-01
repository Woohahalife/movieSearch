import config.MovieConfig;
import controller.MovieOperationController;

public class MovieProcessor {
    public static void main(String[] args) {
        MovieConfig config = new MovieConfig();
        MovieOperationController controller = config.movieOperationController();

        controller.Applicationinit();
    }
}
