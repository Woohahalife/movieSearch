package config;

import controller.MovieOperationController;
import service.MovieOperations;
import service.MovieOperationsImpl;

public class MovieConfig {

    public MovieOperationController movieOperationController() {
        return new MovieOperationController(movieOperations());
    }

    private MovieOperations movieOperations() {
        return new MovieOperationsImpl();
    }


}
