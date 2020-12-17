# BLOG 

## 요구사항
- JBLOG라는 블로그 시스템 개발
- 블로그의 생성/수정/삭제/조회 기능을 구현
- 해당 블로그마다 등록되는 포스트의 생성/수정/삭제및 포스트에 댓글 입력/삭제 기능을 구현
## 유저 시나리오
- 사이트 접속 -> 회원가입 -> 로그인 -> 블로그 생성 -> 블로그 관리 기능
    1. 블로그 관리 기능
        1. 블로그 글 조회 등록 수정 삭제
        1. 블로그 카테고리 조회 등록 수정 삭제
        1. 블로그 정보 수정
        1. 블로그 삭제 요청

- 사이트 접속 -> 블로그 조회 -> 글 목록 조회 -> 글에 대한 댓글 작성
                               

## 구현된 기능 세부사항
### USER
- 회원가입 기능 (ID 중복체크, PASSWORD 중복 체크)
- 로그인 기능
- 로그아웃 기능 
- 회원 탈퇴기능
- ADMIN, MEMBER 권한 분리

### BLOG
- BLOG 전체 List 출력
- 블로그 제목, 해당 블로그 계정 이름(블로거), 태그 세가지 검색 조건과 검색어로 BLOG 검색 기능
- 블로그 생성 (개정마다 하나씩)
- 블로그 정보 수정
- 블로그 삭제 요청, 회원이 BLOG 삭제를 요청하면 ADMIN 권한 관리자가 BLOG 삭제를 처리한다

### CATEGORY
- BLOG에 등록되는 글의 CATEGORY를 생성한다
- CATEGORY를 삭제 수정한다

### POST
- BLOG의 POST List를 보여준다
- BLOG의 CATEGORY에 따라서 POST List를 불러올 수 있다
- POST를 수정 삭제할 수 있다

### COMMENT
- POST 글에 COMMENT을 생성할 수 있다.
- 해당 COMMENT을 수정 삭제할 수 있다
- POST에 관련된 COMMENT List를 조회할 수 있다

## DATABASE DESIGN
![image](https://user-images.githubusercontent.com/12459864/102517090-f739c400-40d2-11eb-90c4-10df61555aff.png)

- DATABASE TABLE 설명
    - BLOG : 블로그 테이블
    - USER : 블로그 계정 테이블
    - CATEGORY : 블로그에 등록된 글들의 카테고리에 관한 테이블
    - POST : 블로그에 등록되는 글에 대한 테이블
    - COMMENT : 포스트마다 등록되는 댓글에 대한 테이블 