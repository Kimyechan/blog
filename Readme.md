# BLOG 

## 요구사항
- JBLOG라는 블로그 시스템 개발
- 블로그의 생성/수정/삭제/조회 기능을 구현
- 해당 블로그마다 등록되는 포스트의 생성/수정/삭제및 포스트에 댓글 입력/삭제 기능을 구현

## DATABASE DESIGN

![image](https://user-images.githubusercontent.com/12459864/102513668-d8d1c980-40ce-11eb-9514-49060782985b.png)

- DATABASE TABLE 설명
    - BLOG : 블로그 테이블
    - USER : 블로그 계정 테이블
    - CATEGORY : 블로그에 등록된 글들의 카테고리에 관한 테이블
    - POST : 블로그에 등록되는 글에 대한 테이블
    - COMMENT : 포스트마다 등록되는 댓글에 대한 테이블 