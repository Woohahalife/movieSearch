package service;

import dto.Movie;

public interface MovieOperations {

    void add(Movie movie);
    Movie[] findAll();

    int findCount(String keyword);
    Movie[] getMovieByKeyword(String keyword, int count);

    Movie[] searchTitle(String title);
    Movie[] searchMajor(String major);
    Movie[] searchGenre(String genre);

    boolean exit();


}
