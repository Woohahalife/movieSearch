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


        System.out.println("<<영화 관리 프로그램>>");

        while (!flag) {
            Movie[] movies = movieOperations.findAll();

            System.out.println("1. 영화 정보 입력");
            System.out.println("2. 전체 영화 목록 검색");
            System.out.println("3. 영화명으로 검색하기");
            System.out.println("4. 영화 주인공으로 검색하기");
            System.out.println("5. 영화 장르로 검색하기");
            System.out.println("6. 영화 정보 삭제");
            System.out.println("-1. 종료");

            int selectNum = sc.nextInt();

            if (selectNum > 6 || (selectNum < 1 && selectNum != -1)) {
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
                    String runningTime = intTime  + "분";
                    System.out.print("평점을 입력해주세요 : ");
                    double rating = sc.nextDouble();
                    System.out.printf("장르를 선택해주세요 %n DRAMA : 1번 / ACTION : 2번 / HORROR : 3번 %n : ");
                    int selectGanre = sc.nextInt();

                    Genre genre = selectGanre == 1 ?
                            Genre.DRAMA : selectGanre == 2 ?
                            Genre.ACTION :
                            Genre.HORROR;

                    movieOperations.add(new Movie(title, major, runningTime, rating, genre));

                    System.out.println("영화가 등록되었습니다.");

                    break;
                case 2:

                    if(movies.length == 0) {
                        System.out.println("영화가 없습니다.");
                    }else {
                        System.out.println(Arrays.toString(movies));
                    }

                    break;
                case 3:
                    if(movies.length == 0) {
                        System.out.print("검색어를 입력해주세요");
                    } else {
                        String setTitle = sc.next();

                        Movie[] moviesByTitle = movieOperations.searchTitle(setTitle);
                        System.out.println(Arrays.toString(moviesByTitle));
                    }
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case -1:
                    System.out.println("종료되었습니다.");
                    flag = movieOperations.exit();
            }
        }
    }

}
