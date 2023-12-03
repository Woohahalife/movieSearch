import config.MovieConfig;
import controller.MovieOperationController;

public class MovieProcessor {
    public static void main(String[] args) {
        MovieConfig config = new MovieConfig();
        MovieOperationController controller = config.movieOperationController();

        long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println("Heap Size(MB) : " + heapSize / (1024 * 1024) + " MB");

        controller.Applicationinit();
    }
}


