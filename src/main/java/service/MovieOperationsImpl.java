package service;

import dto.Movie;

public class MovieOperationsImpl implements MovieOperations {

    private Movie[] movieBox = new Movie[100];
    int index = 0;

    /**
     * 입력받은 movie데이터를 객체배열에 추가하는 API
     * @param movie
     */
    @Override
    public void add(Movie movie) {
        System.out.println("index : " + index);
        movieBox[index++] = movie;
    }





    /**
     * 모든 movie객체배열을 반환하는 API
     * sortIndex API에 의해 순서대로 index가 추가되어 반환됨
     * 평점 기준 내림차순으로 정렬
     * @return sortIndex(findAllMovies)
     */
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





    /**
     * 현재 입력된 movie 데이터의 개수를 세어 반환하는 API
     * @param keyword
     * @return count
     */
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

    /**
     * movie의 총 개수(count) 입력받은 키워드(keyword)를 받아 최종값을 반환하는 메서드
     * @param keyword
     * @param count
     * @return sortIndex(movieByKeyword)
     */
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

    /**
     * 검색한 title에 포함되는 영화목록 검색API(contains)
     * @param title 영화 제목
     * @return getMovieByKeyword(title, count)
     */
    @Override
    public Movie[] searchTitle(String title) {
        int count = findCount(title);

        return getMovieByKeyword(title, count);
    }

    /**
     * 검색한 major에 포함되는 영화목록 검색API(contains)
     * @param major 영화 제목
     * @return getMovieByKeyword(major, count)
     */
    @Override
    public Movie[] searchMajor(String major) {
        int count = findCount(major);

        return getMovieByKeyword(major, count);
    }

    /**
     * 검색한 genre와 일치하는 영화목록 검색API(equals)
     * @param genre 영화 제목
     * @return getMovieByKeyword(genre, count)
     */
    @Override
    public Movie[] searchGenre(String genre) {
        int count = findCount(genre);

        return getMovieByKeyword(genre, count);
    }

    /**
     * 전체 영화 목록 중 index를 선택해 삭제하는 API
     * @param num 전체 영화목록 기준 index와 일치
     */
    @Override
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

        movieBox = new Movie[100];

        index = returnMovies.length;

        sortIndex(returnMovies);
    }





    /**
     * 전체 영화목록을 삭제하는 API
     */
    @Override
    public void removeAll() {

        movieBox = new Movie[100];

        index = 0;
    }





    /**
     * 로직에 의해 추출된 최종 객체배열에 index를 매겨 반환하는 API
     * @param movie 로직에 의해 추출된 객체배열
     * @return
     */
    @Override
    public Movie[] sortIndex(Movie[] movie) {
        if (movie.length >= 1) {
            for (int i=0; i<movie.length; i++) {
                movie[i].setIndex(i + 1);
            }
        }

        return movie;
    }





    /**
     * 애플리케이션을 종료하는 API
     * @return boolean true
     */
    @Override
    public boolean exit(int num) {
        if(num == -1) {
            return true;
        }else {
            System.out.println("초기화면으로 돌아갑니다.");
            return false;
        }
    }
}
