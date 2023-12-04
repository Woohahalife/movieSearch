package service;

import dto.Genre;
import dto.Movie;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovieOperationsImplTest {

    @Test
    void searchTitleTest() {

        // given
        MovieOperations movieOperations = new MovieOperationsImpl();

        Movie movie1 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie2 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie3 = new Movie("Title1", "major", "130", 4.75, Genre.ACTION);

        // when
        movieOperations.add(movie1);
        movieOperations.add(movie2);
        movieOperations.add(movie3);

        int count = movieOperations.findCount("Title");
        Movie[] getMovies = movieOperations.getMovieByKeyword("Title", count);
        Movie[] result = movieOperations.searchTitle("Title");

        // then
        assertThat(count).isEqualTo(2);
        assertThat(getMovies).containsExactlyInAnyOrder(movie1, movie2);
        assertThat(result).containsExactly(getMovies);

    }

    @Test
    void searchMajorTest() {

        // given
        MovieOperations movieOperations = new MovieOperationsImpl();

        Movie movie1 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie2 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie3 = new Movie("Title", "major1", "130", 4.75, Genre.ACTION);

        // when
        movieOperations.add(movie1);
        movieOperations.add(movie2);
        movieOperations.add(movie3);

        int count = movieOperations.findCount("major");
        Movie[] getMovies = movieOperations.getMovieByKeyword("major", count);
        Movie[] result = movieOperations.searchMajor("major");

        // then
        assertThat(count).isEqualTo(2);
        assertThat(getMovies).containsExactlyInAnyOrder(movie1, movie2);
        assertThat(result).containsExactly(getMovies);
    }

    @Test
    void searchGenreTest() {
        // given
        MovieOperations movieOperations = new MovieOperationsImpl();

        Movie movie1 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie2 = new Movie("Title", "major", "130", 4.75, Genre.ACTION);
        Movie movie3 = new Movie("Title", "major1", "130", 4.75, Genre.HORROR);

        // when
        movieOperations.add(movie1);
        movieOperations.add(movie2);
        movieOperations.add(movie3);

        int count = movieOperations.findCount("ACTION");
        Movie[] getMovies = movieOperations.getMovieByKeyword("ACTION", count);
        Movie[] result = movieOperations.searchGenre("ACTION");

        // then
        assertThat(count).isEqualTo(2);
        assertThat(getMovies).containsExactlyInAnyOrder(movie1, movie2);
        assertThat(result).containsExactly(getMovies);
    }
}