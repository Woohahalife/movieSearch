package service;

import dto.Movie;

import java.util.Arrays;

public class MovieOperationsImpl implements MovieOperations {

    private Movie[] movieBox = new Movie[100];
    int index = 0;

    @Override
    public void add(Movie movie) {
        movieBox[index++] = movie;
    }

    @Override
    public Movie[] findAll() {
        Movie[] findAllMovies = new Movie[index];

        // 들어간 영화 개수(index)에 해당하는 공간의 배열 생성

        if (findAllMovies.length != 0) {
            if (index >= 2) {
                for (int i = 0; i < index; i++) {
                    findAllMovies[i] = movieBox[i];
                }
                
                // 정렬 알고리즘
                for (int i = 0; i < index - 1; i++) {
                    for (int j = 0; j < index - i - 1; j++) {
                        if (findAllMovies[j].getRating() < findAllMovies[j + 1].getRating()) {
                            Movie temp;
                            temp = findAllMovies[j];
                            findAllMovies[j] = findAllMovies[j + 1];
                            findAllMovies[j + 1] = temp;
                        }
                    }
                }
            } else {
                findAllMovies[0] = movieBox[0];
            }
        }

        return sortIndex(findAllMovies);
    }

    @Override
    public int findCount(String keyword) {
        int count = 0;

        for (Movie box : movieBox) {

            if (box == null) {
                continue;
            }

            if (box.getTitle().contains(keyword) || box.getMajor().contains(keyword) || box.getGenre().name().equals(keyword)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Movie[] getMovieByKeyword(String keyword, int count) {
        Movie[] movieByKeyword = new Movie[count];

        for (int i = 0; i < index; i++) {
            if (movieBox[i].getTitle().contains(keyword) || movieBox[i].getMajor().contains(keyword) || movieBox[i].getGenre().name().equals(keyword)) {
                movieByKeyword[--count] = movieBox[i];
            }
        }

        return sortIndex(movieByKeyword);
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

    public void removeTarget(int num) {
        Movie[] allMovies = findAll();
        Movie[] returnMovies = new Movie[allMovies.length - 1];
        int targetIndex = num - 1;

        for (int i = 0, k = 0; i < allMovies.length; i++) {
            if (i != targetIndex) {
                returnMovies[k] = allMovies[i];
                k++;
            }
        }

        movieBox = returnMovies;

        index = returnMovies.length;

        sortIndex(returnMovies);
    }

    public void removeAll() {

        movieBox = new Movie[0];

        index = 0;
    }

    @Override
    public Movie[] sortIndex(Movie[] movie) {
        if (movie.length >= 1) {
            for (int i=0; i<movie.length; i++) {
                movie[i].setIndex(i + 1);
            }
        }

        return movie;
    }



    @Override
    public boolean exit() {
        return true;
    }
}
