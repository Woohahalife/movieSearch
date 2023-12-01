package service;

import dto.Movie;
import dto.Genre;

import java.util.Arrays;
import java.util.Objects;
import java.util.zip.DeflaterOutputStream;

public class MovieOperationsImpl implements MovieOperations {

    private final Movie[] movieBox = new Movie[100];
    int index = 0;


    @Override
    public void add(Movie movie) {
        movieBox[index++] = movie;
        movieBox[index-1].setIndex(index);
    }

    @Override
    public Movie[] findAll() {

        Movie[] findAllMovies = new Movie[index];

        for(int i=0; i<index; i++) {
            if(movieBox[i] != null) {
                findAllMovies[i] = movieBox[i];
            }
        }

        return findAllMovies;
    }

    @Override
    public int findCount(String keyword) {

        int count = 0;

        for (Movie box : movieBox) {

            if (box == null) {
                continue;
            }

            if (box.getTitle().equals(keyword) || box.getMajor().equals(keyword) || box.getGenre().name().equals(keyword)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Movie[] getMovieByKeyword(String keyword, int count) {
        Movie[] result = new Movie[count];
        for (int i=0; i<index; i++) {
            if (movieBox[i].getTitle().equals(keyword) || movieBox[i].getMajor().equals(keyword) || movieBox[i].getGenre().name().equals(keyword)) {
                result[--count] = movieBox[i];
            }
        }
        return result;
    }

    @Override
    public Movie[] searchTitle(String title) {
        int count = findCount(title);

        return getMovieByKeyword(title, count);
    }

    @Override
    public Movie[] searchMajor(String major) {
        int count = findCount(major);

        return getMovieByKeyword(major, count);
    }

    @Override
    public Movie[] searchGenre(String genre) {
        int count = findCount(genre);

        return getMovieByKeyword(genre, count);
    }


    @Override
    public boolean exit() {
        return true;
    }
}
