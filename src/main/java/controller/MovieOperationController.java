package controller;

import dto.Movie;
import dto.Genre;
import service.MovieOperations;

import java.util.Arrays;
import java.util.Scanner;

public class MovieOperationController {

    private final MovieOperations movieOperations;

    public MovieOperationController(MovieOperations movieOperations) {
        this.movieOperations = movieOperations;
    }

    public void Applicationinit() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;

        while (!flag) {

            Movie[] movies = movieOperations.findAll();

            System.out.println("┌========= 장르별 영화 검색 프로그램(어..? 이게 왜 돼 조(3조)) ==========┐");
            System.out.println("│ (I) 영화 정보 입력                                                   ");
            System.out.println("│ (P) 전체 영화 목록 출력                                              ");
            System.out.println("│ (S) 영화 검색하기                                              ");
            System.out.println("│ (D) 영화 삭제                                                  ");
            System.out.println("│ (E) 종료                                                         ");
            System.out.println("===================================================================┘");
            System.out.printf("입력 (대소문자 구분X) : ");
            String select = sc.next().toLowerCase();
            String[] searchOption = {"i", "p", "s", "d", "e"};

            if (select.equals(vaildSearchOption(select, searchOption))) {

            } else {
                System.out.println("잘못된 입력입니다.");
            }

            switch (select) {

                /**
                 * 영화 정보 입력
                 */
                case "i":
                    System.out.print("영화 타이틀을 입력해주세요 : ");
                    String title = sc.next();
                    System.out.print("주인공을 입력해주세요 : ");
                    String major = sc.next();
                    System.out.print("상영시간(분)을 입력해주세요 : ");
                    int intTime = sc.nextInt();
                    String runningTime = intTime + "분";
                    System.out.print("평점을 입력해주세요 : ");
                    double rating = sc.nextDouble();
                    System.out.printf("등록할 장르를 선택해주세요 %n DRAMA : 1번 / ACTION : 2번 / HORROR : 3번 %n : ");
                    int selectGanre = sc.nextInt();

                    Genre genre = null;
                    if (selectGanre > 3 || selectGanre < 1) {
                        System.out.println("잘못된 입력입니다.");

                        break;
                    } else {
                        genre = selectGanre == 1 ?
                                Genre.DRAMA : selectGanre == 2 ?
                                Genre.ACTION :
                                Genre.HORROR;
                    }

                    movieOperations.add(new Movie(title, major, runningTime, rating, genre));

                    System.out.println("영화가 등록되었습니다.");

                    break;

                /**
                 * 전체 영화목록 검색(내림차순 정렬)
                 */
                case "p":

                    if (movies.length == 0) {
                        System.out.printf("영화가 없습니다.%n%n");
                        break;
                    } else {
                        System.out.println(Arrays.toString(movies).replace("[", "").replace("]", ""));
                    }

                    break;

                /**
                 * 영화명, 주인공, 장르로 검색
                 */
                case "s":
                    System.out.println("=======================");
                    System.out.println("1.영화명으로 검색하기");
                    System.out.println("2.주인공으로 검색하기");
                    System.out.println("3.장르로 검색하기");
                    System.out.println("=======================");
                    System.out.print("번호 입력 : ");
                    int selectNum = sc.nextInt();

                    if (selectNum > 3 || selectNum < 1) {
                        System.out.println("잘못된 입력입니다! 초기화면으로 돌아갑니다.");
                        break;
                    }

                    switch (selectNum) {

                        case 1:
                        case 2:
                        case 3:
                            while (true) {
                                if (movies.length == 0) {
                                    System.out.printf("영화가 없습니다.%n%n");
                                    break;
                                }

                                if (selectNum == 3) {
                                    System.out.printf("검색할 장르를 선택해주세요 %n DRAMA : 1번 입력 / ACTION : 2번 입력 / HORROR : 3번 입력 %n : ");
                                } else {
                                    System.out.println("검색어를 입력해주세요");
                                }

                                String setKeyword = sc.next();

                                Movie[] moviesBykeyword;
                                if (selectNum == 1) {
                                    moviesBykeyword = movieOperations.searchTitle(setKeyword);

                                    if (moviesBykeyword.length == 0) {
                                        System.out.printf("영화가 없습니다.%n%n");
                                    } else {
                                        System.out.println(Arrays.toString(moviesBykeyword).replace("[", "").replace("]", ""));
                                    }

                                    break;
                                }

                                if (selectNum == 2) {
                                    moviesBykeyword = movieOperations.searchMajor(setKeyword);

                                    if (moviesBykeyword.length == 0) {
                                        System.out.printf("영화가 없습니다.%n%n");
                                    } else {
                                        System.out.println(Arrays.toString(moviesBykeyword).replace("[", "").replace("]", ""));
                                    }

                                    break;
                                }

                                if (selectNum == 3) {
                                    if (setKeyword.equals("1") || setKeyword.equals("2") || setKeyword.equals("3")) {

                                        Genre[] genres = Genre.values();

                                        setKeyword = genres[Integer.parseInt(setKeyword) - 1].name();

                                        moviesBykeyword = movieOperations.searchGenre(setKeyword);

                                        if (moviesBykeyword.length == 0) {
                                            System.out.printf("영화가 없습니다.%n%n");
                                        } else {
                                            System.out.println(Arrays.toString(moviesBykeyword).replace("[", "").replace("]", ""));
                                        }

                                        break;
                                    } else {
                                        System.out.println("잘못된 입력입니다.");
                                    }
                                }
                            }
                            break;
                    }
                    break;


                /**
                 * 영화 정보 삭제 (선택한 영화 삭제 / 전체 삭제 중 선택)
                 */
                case "d":
                    if (movies.length == 0) {
                        System.out.printf("영화가 없습니다.%n%n");

                        break;
                    } else {
                        System.out.println("========================== 영화 목록 ==========================");
                        System.out.println(Arrays.toString(movies).replace("[", "").replace("]", ""));
                        System.out.println("==============================================================");
                        System.out.println();
                    }

                    System.out.println("=======================");
                    System.out.println("1. 선택한 영화 삭제");
                    System.out.println("2. 영화 목록 전체 삭제");
                    System.out.println("=======================");
                    System.out.print("번호 입력 : ");
                    selectNum = sc.nextInt();

                    if (selectNum > 2 || selectNum < 1) {
                        System.out.println("잘못된 입력입니다! 초기화면으로 돌아갑니다.");
                        break;
                    }

                    switch (selectNum) {
                        /**
                         * 선택한 영화 삭제
                         */
                        case 1:

                            System.out.println("삭제할 영화의 번호를 입력해주세요");
                            int num = sc.nextInt();
                            movieOperations.removeTarget(num);

                            System.out.println("해당 영화가 삭제되었습니다.");

                            break;

                        /**
                         * 영화목록 전체 삭제
                         */
                        case 2:
                            if (movies.length == 0) {
                                System.out.printf("영화가 없습니다.%n%n");

                                break;
                            }

                            movieOperations.removeAll();
                            System.out.printf("영화 목록이 전부 삭제되었습니다.%n%n");

                            break;
                    }

                    break;
                case "e":
                    System.out.printf("정말 종료하시겠습니까? %n[ 예 : -1입력 ][ 아니오 : 아무 키나 입력 ]%n 입력하기 : ");
                    selectNum = sc.nextInt();
                    flag = movieOperations.exit(selectNum);
            }
        }
    }

    /**
     * 목록에 해당되지 않는 값을 입력하는 경우 검증결과를 반환하는 API
     * @param select
     * @param searchOption
     * @return select
     */
    private static String vaildSearchOption(String select, String[] searchOption) {
        for (String option : searchOption) {
            if (select.equals(option)) {
                return select;
            }
        }
        return null;
    }
}
