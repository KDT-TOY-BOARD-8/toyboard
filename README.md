# 💻 스프링 부트 - 게시판 관리 프로젝트

<br>

# 📚 목차

* [프로젝트 소개](#1-프로젝트-소개)
* [기술 스택](#2-기술-스택)
    * [백엔드](#2-1-백엔드)
    * [프론트엔드](#2-2-프론트엔드)
* [기능 및 실행 화면](#3-기능-및-실행-화면)
    * [회원 기능](#3-1-회원-기능)
    * [게시판 기능](#3-2-게시판-기능)
    * [댓글 기능](#3-3-댓글-기능)
    * [관리자 기능](#3-4-관리자-기능)
* [구조 및 설계](#4-구조-및-설계)
    * [패키지 구조](#4-1-패키지-구조)
    * [ERD 설계](#4-2-erd-설계)
    * [API 설계](#4-3-api-설계)
* [팀원 역할](#5-팀원-역할)
  <br>

## 1. 프로젝트 소개

🗣️ 이 프로젝트는 스프링 부트와 인텔리제이를 활용한 웹 기반 게시판 관리 시스템으로, 사용자와 관리자를 위한 다양한 기능을 제공합니다.

사용자는 시스템을 통해 회원 가입, 로그인, 개인 정보 수정, 그리고 게시글 작성 및 수정 등의 기능을 이용할 수 있습니다. 특히, 시스템은 회원의 등급에 따라 게시판 접근 권한을 구분하여, 사용자 참여도를 높이는데
효과적입니다.

또한, 관리자는 회원 및 게시글 관리, 블랙리스트 고객 관리, 그리고 통계 확인 등의 기능을 통해 커뮤니티를 효과적으로 관리할 수 있습니다. 이 시스템은 다양한 사용자 요구와 효율적인 커뮤니티 관리를 지원하기 위해
설계되었습니다.

이 외에도 중복체크, 비밀번호 수정, 카테고리별 게시판 구분, 게시글 페이징, 검색, 상세보기, 삭제, 신고 등 다양한 기능을 제공하고 있습니다.

🗓️ 프로젝트 기간 : 7월 10일 (월) ~ 7월 21일 (금)

👨‍👨‍👧‍👧 팀 구성 : 신용호 - 정호윤 - 강경민 - 양수현

## 2. 기술 스택

### 2️-1. 백엔드

`개발환경`

* IntelliJ IDEA

`주요 프레임워크 / 라이브러리`

* JDK 11
* SpringBoot 2.7.x
* Spring Data JPA
* Spring Security
* Spring MyBatis
* Thymeleaf

`빌드 도구`

* Gradle

`데이터베이스`

* MySQL
* Docker

### 2-2. 프론트엔드

* Html/CSS
* JavaScript

## 3. 기능 및 실행 화면

### 3-1. 회원 기능

<table>
  <tr>
    <td>회원가입</td>
    <td>로그인</td>
    <td>유저 네임 중복체크</td>
  </tr>
  <tr>
    <td>회원 정보 보기</td>
    <td>회원 정보 수정</td>
    <td>비밀번호 수정</td>
  </tr>
</table>

<details>
    <summary><strong>회원가입 실행화면</strong> </summary>
   - 회원가입 페이지에서 회원가입 구현 (id, username, password, email, nickName, role, createdAt, updatedAt)
   <br>
   - role은 새싹회원과 우수회원으로 구분(디폴트: 새싹회원, 게시글 수 10개 이상 우수 회원)
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/7c73074d-122f-4b12-9db0-e73e8c0e1779" />
</details>
<details>
    <summary><strong>로그인 실행화면</strong> </summary>
   - 로그인 페이지에서 로그인 구현 (username, password)
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/6325f395-560c-47dc-a7ac-21032bd2e463" />
</details>
<details>
  <summary><strong>회원정보 보기 실행화면</strong> </summary>
  - 회원정보 페이지에서 username, email, role, createdAt 확인
  <br>
  <br>
  <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/e0b75834-2a3c-4e07-b086-b96b9a1892e2" />
</details>
<details>
    <summary><strong>회원 정보 수정 실행화면</strong> </summary>
   - 회원정보 수정페이지에서 email, nickName 변경가능
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/e87994a4-cc23-4d84-acb6-eb266096667c" />
</details>
<details>
    <summary><strong>비밀번호 수정 실행화면</strong> </summary>
   - 비밀번호 수정 페이지에서 비밀번호 수정 구현
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/60425058-d154-4f97-bc74-847ed7614ea0" />
</details>

### 3-2. 게시판 기능

<table>
  <tr>
    <td>게시글 카테고리</td>
    <td>게시글 쓰기</td>
    <td>게시글 목록 보기</td>
  </tr>
  <tr>
    <td>게시글 페이징</td>
    <td>게시글 검색</td>
    <td>게시글 상세 보기</td>
  </tr>
  <tr>
    <td>게시글 삭제</td>
    <td>게시글 수정</td>
    <td>게시글 신고</td>
  </tr>
</table>

<details>
    <summary><strong>게시글 카테고리 실행화면</strong> </summary>
   - 새싹회원 게시판, 우수회원 게시판 구현
   <br> (게시판은 2개이지만 하나의 화면을 공유해서 사용하고 카테고리로 구분함)
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/606d2517-e8c7-47ba-bb9c-64ca30381755" />
</details>
<details>
    <summary><strong>게시글 쓰기 실행화면</strong> </summary>
   - 게시글 쓰기 페이지 에서 권한(새싹, 우수)에 따라 다른 게시판에 글이 적어짐
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/4cb3339b-fd2b-4a2a-afed-f671dc8faa0e" />
</details>
<details>
    <summary><strong>게시글 상세보기 실행화면</strong> </summary>
   - 게시글 상세보기 페이지에서 id, title, content, nickName, 댓글의 comment 리스트(id, comment, 댓글의 작성자 nickName) 이 화면에 보여야 함.
   - 게시글 삭제버튼과 수정버튼 보여야 함(본인이 적은 글에 대해서만)
   - 댓글 삭제버튼이 보여야함(본인이 적은 댓글에 대해서만)
   <br>
   <br> 
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/0ec05f65-1d2b-47b0-889a-dd34a11b0ea0" />
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/fd793f9e-44ab-462b-b108-47a6995019c9" /> 
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/f59e34e6-36d6-44c9-b510-5c7695cf1db9" />
</details>

<details>
  <summary><strong>게시글 수정 실행화면</strong> </summary> - 게시글 수정하기 페이지에서 title, content 수정 가능 <br>
  - <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/85f2415c-7f67-44e7-b9e8-ac4069875361"/>
</details>
<details>
    <summary><strong>게시글 신고 실행화면</strong> </summary>
   - 게시글 상세보기 페이지에서 게시글 신고가능 (형태 : 욕설, 음란, 비방)
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/7a7b82fc-ccef-44b8-915a-3fa2a27a4542"/>
</details>

### 3-3. 댓글 기능

<table>
  <tr>
    <td>댓글 쓰기</td>
    <td>댓글 삭제</td>
  </tr>
</table>

<details>
  <summary><strong>댓글 쓰기 실행화면</strong> </summary>
  - 게시글 상세보기 페이지에서 댓글 쓰기 50자이내, 댓글에 댓글을 작성할 수 있음. 대댓글 기능 구현(depth 1까지)
   <br>
   <br>
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/02e41c9f-79f1-4f0d-9f45-8d86af42db9c" />
   <img src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/115003898/14f84e02-98a4-4fbb-b225-92eb0be23bd8" />
</details>


### 3-4. 관리자 기능

<table>
  <tr>
    <td>관리자 회원 권한 관리</td>
    <td>관리자 회원 Email 전송 관리</td>
    <td>관리자 게시글 CRUD 관리</td>
  </tr>
  <tr>
    <td>관리자 게시글 통계 관리</td>
    <td>관리자 블랙리스트 고객 등록</td>
    <td>관리자 블랙리스트 고객 해제</td>
  </tr>
</table>

## 4. 구조 및 설계

### 4-1. 패키지 구조

<details>
    <summary><strong>패키지 구조 보기</strong> </summary>
```
📦 
├─ .gitignore
├─ README.md
├─ build.gradle
├─ docker-compose.yml
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ fastcampus
   │  │        └─ toyboard
   │  │           ├─ ToyboardApplication.java
   │  │           ├─ board
   │  │           │  ├─ controller
   │  │           │  │  └─ BoardController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ BoardDto.java
   │  │           │  │  ├─ BoardRequestDto.java
   │  │           │  │  ├─ BoardResponseDto.java
   │  │           │  │  ├─ BoardResponseWithComment.java
   │  │           │  │  └─ BoardWithCommentDto.java
   │  │           │  ├─ model
   │  │           │  │  └─ Board.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ BoardRepository.java
   │  │           │  │  └─ IBoard.java
   │  │           │  └─ service
   │  │           │     └─ BoardService.java
   │  │           ├─ comment
   │  │           │  ├─ controller
   │  │           │  │  ├─ ChildCommentController.java
   │  │           │  │  └─ CommentController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ ChildCommentDto.java
   │  │           │  │  ├─ ChildCommentRequestDto.java
   │  │           │  │  ├─ ChildCommentResponse.java
   │  │           │  │  ├─ CommentDto.java
   │  │           │  │  ├─ CommentRequestDto.java
   │  │           │  │  ├─ CommentResponseDto.java
   │  │           │  │  └─ CommentResponseWithChildren.java
   │  │           │  ├─ model
   │  │           │  │  ├─ ChildComment.java
   │  │           │  │  └─ Comment.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ ChildCommentRepository.java
   │  │           │  │  └─ CommentRepository.java
   │  │           │  └─ service
   │  │           │     ├─ ChildCommentService.java
   │  │           │     └─ CommentService.java
   │  │           ├─ common
   │  │           │  ├─ MainController.java
   │  │           │  ├─ exceptions
   │  │           │  │  ├─ ErrorController.java
   │  │           │  │  └─ ToyboardInternalExceptionHandler.java
   │  │           │  └─ jpa
   │  │           │     └─ BaseTimeEntity.java
   │  │           ├─ config
   │  │           │  ├─ JpaConfig.java
   │  │           │  ├─ ThymeleafConfig.java
   │  │           │  └─ security
   │  │           │     ├─ BoardUserAuthenticationFilter.java
   │  │           │     ├─ BoardUserManager.java
   │  │           │     ├─ BoardUserToken.java
   │  │           │     ├─ PasswordEncoderConfig.java
   │  │           │     └─ SecurityConfig.java
   │  │           ├─ report
   │  │           │  ├─ controller
   │  │           │  │  └─ ReportController.java
   │  │           │  ├─ dto
   │  │           │  │  └─ ReportRequestDto.java
   │  │           │  ├─ model
   │  │           │  │  ├─ Report.java
   │  │           │  │  └─ ReportType.java
   │  │           │  ├─ repository
   │  │           │  │  └─ ReportRepository.java
   │  │           │  └─ service
   │  │           │     └─ ReportService.java
   │  │           └─ user
   │  │              ├─ controller
   │  │              │  └─ BoardUserController.java
   │  │              ├─ dto
   │  │              │  ├─ BoardUserDto.java
   │  │              │  └─ BoardUserRequest.java
   │  │              ├─ model
   │  │              │  ├─ BoardAuthority.java
   │  │              │  └─ BoardUser.java
   │  │              ├─ repository
   │  │              │  ├─ BoardAuthorityRepository.java
   │  │              │  └─ BoardUserRepository.java
   │  │              └─ service
   │  │                 └─ BoardUserService.java
   │  └─ resources
   │     ├─ application.yml
   │     ├─ data.sql
   │     ├─ static
   │     │  └─ assets
   │     │     ├─ css
   │     │     ├─ img
   │     │     ├─ js
   │     │     ├─ scss
   │     │     └─ vendor
   │     │        ├─ aos
   │     │        ├─ bootstrap-icons
   │     │        ├─ bootstrap
   │     │        ├─ glightbox
   │     │        ├─ php-email-form
   │     │        └─ swiper
   │     └─ templates
   │        ├─ board
   │        │  ├─ detail.html
   │        │  ├─ detail.th.xml
   │        │  ├─ index.html
   │        │  ├─ index.th.xml
   │        │  ├─ report-post.html
   │        │  ├─ report-post.th.xml
   │        │  ├─ update-post.html
   │        │  ├─ update-post.th.xml
   │        │  ├─ write-post.html
   │        │  └─ write-post.th.xml
   │        ├─ common
   │        │  ├─ footer.html
   │        │  ├─ head.html
   │        │  ├─ header.html
   │        │  └─ header.th.xml
   │        ├─ error.html
   │        ├─ error.th.xml
   │        ├─ error
   │        │  ├─ error400.html
   │        │  ├─ error400.th.xml
   │        │  ├─ error403.html
   │        │  ├─ error403.th.xml
   │        │  ├─ error404.html
   │        │  ├─ error404.th.xml
   │        │  ├─ error500.html
   │        │  └─ error500.th.xml
   │        ├─ login-failed.html
   │        ├─ login-failed.th.xml
   │        ├─ login.html
   │        ├─ login.th.xml
   │        ├─ sign-up.html
   │        ├─ sign-up.th.xml
   │        └─ user
   │           ├─ edit.html
   │           ├─ edit.th.xml
   │           ├─ editPw.html
   │           ├─ editPw.th.xml
   │           ├─ my-info.html
   │           └─ my-info.th.xml
   └─ test
      └─ java
         └─ com
            └─ fastcampus
               └─ toyboard
                  └─ ToyboardApplicationTests.java
```
©generated by [Project Tree Generator](https://woochanleee.github.io/project-tree-generator)    

</details> 

### 4-2. ERD 설계
<details>
    <summary><strong>ERD 설계</strong> </summary> 
<img width="800" alt="스크린샷 2023-07-20 오후 9 43 02" src="https://github.com/KDT-TOY-BOARD-8/toyboard-user/assets/111266513/620f4ffd-1ac5-4e87-9c73-0adc114ed5db"> </details>
  

### 4-3. API 설계

<details>
    <summary><strong>회원 API</strong> </summary> 
<img width="800" alt="스크린샷 2023-07-20 오후 9 05 35" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/bd5c7c5a-a71f-4105-b52c-80e7a04a3b15"> </details>
<details>
    <summary><strong>게시판 API</strong> </summary> <img width="800" alt="스크린샷 2023-07-20 오후 8 38 14" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/ebc00e10-ce46-403b-87a0-a11b8fe48473"> </details>
 <details>
    <summary><strong>댓글 API</strong> </summary> <img width="801" alt="스크린샷 2023-07-20 오후 8 38 47" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/377cc1c2-fea2-4414-aeaf-92f4ab832e7c"> </details>
 <details>
    <summary><strong>관리자 API</strong> </summary> <img width="800" alt="스크린샷 2023-07-20 오후 9 43 02" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/7254ae23-e5b1-49ae-be30-684ae713017a"> </details>

## 5. 팀원 역할

👨‍💻`정호윤`

- 회원 정보 수정 기능 구현
- 회원 정보 수정 페이지 구현
- 회원 가입 기능 구현
- 회원 가입 페이지 구현

👩‍💻`양수현`

- 게시글 기능 구현
- 게시글 신고 기능 구현

👨‍💻`신용호`

- 브렌치 관리 및 프로젝트 전반 진행 총괄
- 유저 프론트 페이지 구현
- 스프링 시큐리티 초기 설정 및 회원 로그인 구현
- 유저 DB 설정 (with Docker compose)
- 댓글/대댓글 기능 구현

👩‍💻`강경민`

- 관리자 기능 구현
- 관리자 페이지 구현
