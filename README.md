# JAVA 실습 과제 (장르별 영화 검색 프로그램)   
장르별 영화 검색 프로그램의 초기 화면 구성(콘솔 활용)

## 진행기간 
2023.12.01.(금) 19:00 ~ 2023.12.05.(화) 09:50   

## 요구 사항
1. 아래의 처리 조건을 만족하는 장르별 영화 검색 프로그램을 설계 및 구현하십시오.   
2. 사용되는 클래스는 다음과 같이 세 가지입니다.   
- MovieDTO, MovieProcessor (메인 클래스), MovieOperations (기능 구현 클래스)
3. 입력 화면 및 처리 조건을 보고 각 그룹에서 구현한 후 제출하십시오.   
4. 과제 수행을 위해 허용되는 범위 내에서 입력/출력 화면을 자유롭게 구성하십시오.   
5. 추가 데이터를 입력하여 프로그램을 테스트하고 모든 기능이 작동하는지 확인하십시오.   
예상대로 작동하는지 확인해야 합니다.   
[Challenge Points] - 그룹별로 1가지 기능을 차별화 하여 추가적으로 구현 할 것 - **20점**   
   
**과제 상세 설명**   
1. 장르별 영화 검색 프로그램의 초기 화면 구성 - **15점**   
======== 장르별 영화 검색 프로그램(그룹 이름) ==========   
1.영화입력(I) 2.영화출력(P) 3.영화검색(S) 4.종료(E)   
=============================================
   
메뉴입력:   
2. 영화입력(I) 상세설명 - **20점**   
- 스캐너를 사용하여 영화 데이터를 받습니다.   
- 먼저, 영화 데이터의 수를 입력하고 MovieDTO 배열을 사용하여 각 영화의 세부 사항을 저장합니다.   
- 영화 세부정보는 제목(title), 주인공(major), 상영시간(runningTime), 평점(rating), 장르(genre)로 구성됩니다.   
- 장르는 1(드라마), 2(액션), 3(호러)로 구성됩니다.   
- 입력 도중 데이터를 더 이상 받고 싶지 않은 경우, 제목에 '-1'을 입력하여 추가 입력을 방지합니다.   
입력예시)   
영화1: '기생충', 주연: '김기택', 러닝타임: 132, 평점: 8.6, 장르: 1   
영화2: '올드보이', 주인공: '오대수', 러닝타임: 120, 평점: 8.4, 장르: 2   
영화3: '괴물', 주인공: '박강두', 러닝타임: 119, 평점: 7.1, 장르: 3


3. 영화출력(P) 상세설명 - **15점**   
- 평점을 기준으로 내림차순으로 정렬하여 출력해야 합니다.
     
4. 영화검색(S) 상세설명 - **15점**   
- 검색할 장르를 입력합니다(1, 2, 3 중 하나).   
- 입력된 장르에 해당하는 영화를 검색하여 출력합니다.
     
5. 종료(E) 상세설명 - **5점**   
- 메뉴에서 -1을 입력하면 전체 프로그램이 종료됩니다.
   
6. 오류 처리에 대한 상세 설명 - **10점**   
- 메뉴를 입력할 때 I, S, P, E 중 하나가 아닌 경우 오류 메시지가 출력되고 메뉴가 다시 입력됩니다.   
- 영화를 검색할 때 장르 1, 2, 3 중 하나가 아닌 경우 오류 메시지가 출력되고 장르가 다시 입력됩니다.   

## 패키지 구조
─main
│  ├─java
│  │  │  MovieProcessor.java (main)
│  │  │
│  │  ├─config
│  │  │      MovieConfig.java
│  │  │
│  │  ├─controller
│  │  │      MovieOperationController.java
│  │  │
│  │  ├─dto
│  │  │   Genre.java
│  │  │   Movie.java
│  │  │
│  │  └─service
│  │          MovieOperations.java
│  │          MovieOperationsImpl.java
│  │
│  └─resources
└─test
    ├─java
    │  └─service
    │          MovieOperationsImplTest.java
    │
    └─resources



