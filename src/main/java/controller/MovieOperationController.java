package controller;

import dto.Movie;
import dto.Genre;
import service.MovieOperations;

import java.sql.SQLOutput;
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


        System.out.println("<<영화 관리 프로그램>>");

        while (!flag) {

            Movie[] movies = movieOperations.findAll();

            System.out.println("1. 영화 정보 입력");
            System.out.println("2. 전체 영화 목록 검색");
            System.out.println("3. 영화명으로 검색하기");
            System.out.println("4. 영화 주인공으로 검색하기");
            System.out.println("5. 영화 장르로 검색하기");
            System.out.println("6. 영화 정보 삭제");
            System.out.println("7. 영화 목록 전체 삭제");
            System.out.println("-1. 종료");

            int selectNum = sc.nextInt();

            if (selectNum > 7 || (selectNum < 1 && selectNum != -1)) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
            }

            switch (selectNum) {

                case 1:
                    System.out.print("영화 타이틀을 입력해주세요 : ");
                    String title = sc.next();
                    System.out.print("주인공을 입력해주세요 : ");
                    String major = sc.next();
                    System.out.print("상영시간(분)을 입력해주세요 : ");
                    int intTime = sc.nextInt();
                    String runningTime = intTime + "분";
                    System.out.print("평점을 입력해주세요 : ");
                    double rating = sc.nextDouble();
                    System.out.printf("장르를 선택해주세요 %n DRAMA : 1번 / ACTION : 2번 / HORROR : 3번 %n : ");
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
                case 2:

                    if (movies.length == 0) {
                        System.out.printf("영화가 없습니다.%n%n");
                        break;
                    } else {
                        System.out.println(Arrays.toString(movies));
                    }

                    break;

                /**
                 * 영화명, 주인공, 장르로 검색
                 */
                case 3:
                case 4:
                case 5:
                    while (true) {
                        if (movies.length == 0) {
                            System.out.printf("영화가 없습니다.%n%n");
                            break;
                        }

                        if (selectNum == 5) {
                            System.out.printf("검색할 장르를 선택해주세요 %n DRAMA : 1번 입력 / ACTION : 2번 입력 / HORROR : 3번 입력 %n : ");
                        } else {
                            System.out.println("검색어를 입력해주세요");
                        }

                        String setKeyword = sc.next();

                        Movie[] moviesBykeyword;
                        if (selectNum == 3) {
                            moviesBykeyword = movieOperations.searchTitle(setKeyword);

                            if(moviesBykeyword.length == 0) {
                                System.out.printf("영화가 없습니다.%n%n");
                            }else {
                                System.out.println(Arrays.toString(moviesBykeyword));
                            }

                            break;
                        }

                        if (selectNum == 4) {
                            moviesBykeyword = movieOperations.searchMajor(setKeyword);

                            if(moviesBykeyword.length == 0) {
                                System.out.printf("영화가 없습니다.%n%n");
                            }else {
                                System.out.println(Arrays.toString(moviesBykeyword));
                            }

                            break;
                        }

                        if (selectNum == 5) {
                            if (setKeyword.equals("1") || setKeyword.equals("2") || setKeyword.equals("3")) {

                                Genre[] genres = Genre.values();

                                setKeyword = genres[Integer.parseInt(setKeyword) - 1].name();

                                moviesBykeyword = movieOperations.searchGenre(setKeyword);

                                if(moviesBykeyword.length == 0) {
                                    System.out.printf("영화가 없습니다.%n%n");
                                }else {
                                    System.out.println(Arrays.toString(moviesBykeyword));
                                }

                                break;
                            } else {
                                System.out.println("잘못된 입력입니다.");
                            }
                        }
                    }
                    break;

                /**
                 * 영화 정보 삭제
                 */
                case 6:
                    if (movies.length == 0) {
                        System.out.printf("영화가 없습니다.%n%n");

                        break;
                    } else {
                        System.out.println(Arrays.toString(movies));
                    }

                    System.out.println("삭제할 영화의 번호를 입력해주세요");
                    int num = sc.nextInt();
                    movieOperations.removeTarget(num);

                    System.out.println("해당 영화가 삭제되었습니다.");


                    break;

                /**
                 * 영화목록 전체 삭제
                 */
                case 7:
                    if (movies.length == 0) {
                        System.out.printf("영화가 없습니다.%n%n");

                        break;
                    }

                    movieOperations.removeAll();
                    System.out.printf("영화 목록이 전부 삭제되었습니다.%n%n");

                    break;
                case -1:
                    System.out.println("종료되었습니다.");
                    flag = movieOperations.exit();
            }
        }
    }
}
