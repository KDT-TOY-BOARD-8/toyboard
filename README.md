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

사용자는 시스템을 통해 회원 가입, 로그인, 개인 정보 수정, 그리고 게시글 작성 및 수정 등의 기능을 이용할 수 있습니다. 특히, 시스템은 회원의 등급에 따라 게시판 접근 권한을 구분하여, 사용자 참여도를 높이는데 효과적입니다.

또한, 관리자는 회원 및 게시글 관리, 블랙리스트 고객 관리, 그리고 통계 확인 등의 기능을 통해 커뮤니티를 효과적으로 관리할 수 있습니다. 이 시스템은 다양한 사용자 요구와 효율적인 커뮤니티 관리를 지원하기 위해 설계되었습니다.

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
    <summary><strong>회원가입 실행화면</strong> </summary> - 회원가입 페이지에서 회원가입 구현 (id, username, password, email, nickName, role, createdAt, updatedAt) <br> - role은 새싹회원과 우수회원으로 구분(디폴트: 새싹회원, 게시글 수 10개 이상 우수 회원) <br> - 스크린샷첨부(이 부분 글 삭제하고 여기에 넣으면 될것 같아요!!) </details>
<details>
    <summary><strong>로그인 실행화면</strong> </summary> - 로그인 페이지에서 로그인 구현 (username, password) <br> - 스크린샷첨부  </details>
 <details>
    <summary><strong>로그아웃 실행화면</strong> </summary> - 로그아웃 구현 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>유저네임 중복체크 실행화면</strong> </summary> - 회원가입 페이지에서 동일 username 중복체크하기 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>회원정보 보기 실행화면</strong> </summary> - 회원정보 페이지에서 username, email, role, createdAt 확인 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>회원 정보 수정 실행화면</strong> </summary> - 회원정보 수정페이지에서 email, nickName 변경가능 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>비밀번호 수정 실행화면</strong> </summary> - 비밀번호 수정 페이지에서 비밀번호 수정 구현 <br> - 스크린샷첨부  </details>
   
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
    <summary><strong>게시글 카테고리 실행화면</strong> </summary> - 새싹회원 게시판, 우수회원 게시판 구현 (게시판은 2개이지만 하나의 화면을 공유해서 사용하고 카테고리로 구분함) <br> - 스크린샷첨부(이 부분 글 삭제하고 여기에 넣으면 될것 같아요!!) </details>
<details>
    <summary><strong>게시글 쓰기 실행화면</strong> </summary> - 게시글 쓰기 페이지 에서 권한(새싹, 우수)에 따라 다른 게시판에 글이 적어짐 (썸머노트 적용) <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>게시글 목록보기 실행화면</strong> </summary> - 게시글 목록보기 페이지에서 게시글 목록보기 (id, title, content, thumbnail(게시글 사진), user의 nickName 화면에 보여야 함, content내용을 화면에 2줄이 넘어가면 Ellipsis(...)으로 스타일 변경, 정렬은 id순 Desc <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>게시글 페이징 실행화면</strong> </summary> - 게시글 목록보기 페이지에서 페이지당 6개 게시글 보여야 함, 게시글은 Grid 형식으로 3개씩 카드(Card) 배치 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>게시글 검색 실행화면</strong> </summary> - 게시글 목록보기 페이지에서 작성자(nickName), 제목(title), 내용(content)로 검색 가능 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>게시글 상세보기 실행화면</strong> </summary> - 게시글 상세보기 페이지에서 id, title, content, nickName, 댓글의 comment 리스트(id, comment, 댓글의 작성자 nickName) 이 화면에 보여야 함. 게시글 삭제버튼과 수정버
    튼 보여야 함(본인이 적은 글에 대해서만), 댓글 삭제버튼이 보여야함(본인이 적은 댓글에 대해서만) <br> - 스크린샷첨부  </details>
    <details>
    <summary><strong>게시글 삭제 실행화면</strong> </summary> - 게시글 상세보기 페이지에서 본인이 적은 게시글만 삭제가능 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>게시글 수정 실행화면</strong> </summary> - 게시글 수정하기 페이지에서 title, content 수정 가능 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>게시글 신고 실행화면</strong> </summary> - 게시글 상세보기 페이지에서 게시글 신고가능 (형태 : 욕설, 음란, 비방) <br> - 스크린샷첨부  </details>
    
### 3-3. 댓글 기능
<table>
  <tr>
    <td>댓글 쓰기</td>
    <td>댓글 삭제</td>
    <td>스케쥴러 등극</td>
  </tr>
</table>

<details>
    <summary><strong>댓글 쓰기 실행화면</strong> </summary> - 게시글 상세보기 페이지에서 댓글 쓰기 50자이내, 댓글에 댓글을 작성할 수 있음. 대댓글 기능 구현(depth 1까지) <br> - 스크린샷첨부(이 부분 글 삭제하고 여기에 넣으면 될것 같아요!!) </details>
<details>
    <summary><strong>댓글 삭제 실행화면</strong> </summary> - 게시글 상세보기 페이지에서 댓글 삭제가능(댓글은 수정은 없음) <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>스케쥴러 등극 실행화면</strong> </summary> - @Schedule 을 사용하여, 1분에 한번씩 게시글 수가 10개인데, 우수회원이 아닌 새싹회원 등급 자동 변경 <br> - 스크린샷 첨부 </details>

    
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

<details>
    <summary><strong>관리자 회원 권환 관리 실행화면</strong> </summary> - 회원의 role 변경 가능 <br> - 스크린샷첨부(이 부분 글 삭제하고 여기에 넣으면 될것 같아요!!) </details>
<details>
    <summary><strong>관리자 회원 Email 전송 관리 실행화면</strong> </summary> - 회원에게 email 전송 가능 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>관리자 게시글 CRUD 실행화면</strong> </summary> - 게시글 목록보기, 삭제하기, 숨기기/보이기, 블랙리스트(욕설) 등록 가능 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>관리자 게시글 통계 관리 실행화면</strong> </summary> - 유저의 게시글 수, 댓글 수를 볼 수 있고, 댓글수가 많은 유저 순, 게시글수가 많은 유저순으로 정렬 가능 <br> - 스크린샷첨부  </details>
<details>
    <summary><strong>관리자 블랙리스트 고객 등록 실행화면</strong> </summary> - 게시글 신고목록 페이지 구현, 해당 페이지에서 블랙리스트 고객 등록 가능 <br> - 스크린샷 첨부 </details>
<details>
    <summary><strong>관리자 블랙리스트 고객 해제 실행화면</strong> </summary> - 게시글 신고목록 페이지 구현, 해당 페이지에서 블랙리스트 고객 해제 가능 <br> - 스크린샷첨부  </details>


## 4. 구조 및 설계
### 4-1. 패키지 구조
<details>
    <summary><strong>패키지 구조 보기</strong> </summary> - 프로젝트 완성 후 넣기 </details> 
    
### 4-2. ERD 설계
- 수정할 부분 있으면 수정후 다시 이미지 올리기
![Toyboard (1)](https://github.com/YangSooHyun0/TIL/assets/111266513/a4084c21-413a-4667-929b-23e7b480fb63)

### 4-3. API 설계
<details>
    <summary><strong>회원 API</strong> </summary> 
<img width="547" alt="스크린샷 2023-07-20 오후 9 05 35" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/bd5c7c5a-a71f-4105-b52c-80e7a04a3b15"> </details>
<details>
    <summary><strong>게시판 API</strong> </summary> <img width="500" alt="스크린샷 2023-07-20 오후 8 38 14" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/ebc00e10-ce46-403b-87a0-a11b8fe48473"> </details>
 <details>
    <summary><strong>댓글 API</strong> </summary> <img width="801" alt="스크린샷 2023-07-20 오후 8 38 47" src="https://github.com/YangSooHyun0/Spring-Boot-JPA/assets/111266513/377cc1c2-fea2-4414-aeaf-92f4ab832e7c"> </details>
 <details>
    <summary><strong>관리자 API</strong> </summary> - 노션 스크린샷 첨부 </details>
    
  
## 5. 팀원 역할
👨‍💻`정호윤`
-
-
-
<br>

👩‍💻`양수현`
- 
-
-

<br>

👨‍💻`신용호`
- 
-
-

<br>

👩‍💻`강경민`
- 
-
-
