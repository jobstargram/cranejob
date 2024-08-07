# 2차 프로젝트 (CraneJob)
## 1. 목표

### 1.1 목표
- 개발자 취준생을 위한 채용 정보 제공
  - 최신 채용 정보를 한 곳에서 쉽게 접근할 수 있도록 제공
  - 효율적인 취업 준비를 위한 정보 플랫폼 구축
- 웹 개발 기본 소양 습득
  - CRUD 기능을 직접 구현하여 웹 개발의 기초 다지기
  - 실전 프로젝트를 통해 실무 능력 향상
- 협업 경험 및 실전 프로젝트 수행
  - 팀원 간 원활한 협업을 통한 프로젝트 수행 경험 축적
  - 실시간 피드백을 통한 문제 해결 및 프로젝트 완성도 향상

### 1.2 기능
- 채용 정보 수집 및 업데이트
  - 사람인에서 채용 정보를 자동으로 수집 및 매일 업데이트
  - 최신 채용 정보를 사용자에게 제공
- 사용자 관리 및 권한 분리
  - 관리자와 일반 사용자의 권한을 구분하여 관리
  - 관리자는 게시판과 공지사항을 관리, 일반 사용자는 정보 열람 및 댓글 작성 가능
- 게시판 기능
  - 채용 정보를 게시판 형태로 제공
  - 사용자 간 정보 공유 및 의견 교환을 위한 댓글 기능 제공
- 공지사항 기능
  - 관리자에 의해 게시되는 공지사항 제공
  - 중요한 정보 및 업데이트 사항 공지
- 댓글 기능
  - 사용자 간의 커뮤니케이션을 위한 댓글 작성 및 조회 기능
  - 다양한 의견 교환 및 정보 공유 활성화
- 시큐리티 로직 및 예외처리
  - 보안 강화 및 사용자 데이터 보호를 위한 시큐리티 로직 구현
  - 다양한 예외 상황에 대한 처리 로직 설계 및 적용

### 1.3 팀 구성
<table>
	<tr>
		<th>이정석</th>
		<th>조아라</th>
		<th>조준호</th>
		<th>이소정B</th>
	</tr>
 	<tr>
		<td><img src="![cranejob-logo-1](https://github.com/user-attachments/assets/55994c4b-18ab-45d1-8d2e-45412a66a9aa)" width="100%"></td>
		<td><img src="![cranejob-logo-1](https://github.com/user-attachments/assets/55994c4b-18ab-45d1-8d2e-45412a66a9aa)" width="100%"></td>
		<td><img src="![cranejob-logo-1](https://github.com/user-attachments/assets/55994c4b-18ab-45d1-8d2e-45412a66a9aa)" width="100%"></td>
		<td><img src="![cranejob-logo-1](https://github.com/user-attachments/assets/55994c4b-18ab-45d1-8d2e-45412a66a9aa)" width="100%"></td>
	</tr>
</table>

## 2. 개발 환경 
### 2.1 개발 환경
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### 2.2 배포 URL
- http://3.38.247.239/ 
- 테스트용 계정
  ```
  admin
  id : admin@gmail.com
  pw : admin123!@#

  user
  id : user@gmail.com
  pw : user123!@#
  ```
  
### 2.3 URL 구조(모놀리식)
### 📝 회원 가입

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| userSignUpForm | GET | /user/signup | 일반 회원 가입 폼 요청 | ALL |
| userSignUp | POST | /user/signup | 일반 회원 가입 요청 | ALL |
| userDelete | PATCH | /user/delete{username} | 회원 탈퇴 요청(RESTful API) | USER |
| adminSignUpForm | GET | /admin/signup | 관리자 회원가입 폼 요청 | ALL |
| adminSignUp | POST | /admin/signup | 관리자 회원 가입 요청 | ALL |

### 🔐 로그인

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| userLoginForm | GET | /user/login | 일반 회원 로그인 폼 요청 | ALL |
| userLogout | GET | /user/logout | 일반 회원 로그아웃 | USER |
| adminLoginForm | GET | /user/login | 관리자 로그인 폼 요청 | ALL |
| adminLogout | GET | /user/logout | 관리자 로그아웃 | ADMIN |

### 🧑 유저 정보

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| userEditForm | GET | /user/edit | 일반 회원 정보 수정 폼 요청 | USER |
| userEdit | PUT | /user/edit | 일반 회원 수정 요청 | USER |
| adminEditForm | GET | /admin/edit | 관리자 회원 정보 수정 폼(일반 회원과 동일) 요청 | ADMIN |
| adminEdit | PUT | /admin/edit | 관리자 회원 수정(일반 회원과 동일) 요청 | ADMIN |
| userList | GET | /admin/users | 회원 정보 리스트 조회 | ADMIN |
| userDetails | GET | /admin/users/{username} | 특정 회원 상세 정보 조회(상태, 권한등) | ADMIN |
| updateUserRole | POST | /admin/users/edit | 특정 회원 상태 및 권한 정보 수정 | ADMIN |

### 📄 게시글

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| listPost | GET | /post/list | 게시글 리스트 조회 | ALL |
| createPostForm | GET | /post/form | 게시글 생성 폼 요청 | USER |
| createPost | POST | /post/form | 게시글 생성 요청 | USER |
| postDetail | GET | /post/detail/{id} | 게시글 상세 정보 조회 | USER(작성자) |
| editPostForm | GET | /post/edit/{id} | 게시글 수정 폼 요청 | USER(작성자) |
| updatePost | PUT | /post/edit/{id} | 게시글 수정 요청 | USER(작성자) |
| deletePost | POST | /post/delete/{id} | 게시글 삭제 요청(soft delete) | USER(작성자) |

### 💬 댓글(RESTful API)

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| getComments | GET | /api/comment/{postId} | 댓글 리스트 조회 | USER |
| addComment | POST | /api/comment/{postId} | 댓글 작성 요청 | USER |
| updateComment | PUT | /api/comment/{commentId} | 댓글 수정 요청 | USER(작성자) |
| deleteComment | DELETE | /api/comment/{commentId} | 댓글 삭제 요청 | USER(작성자) |

### 📢 공지사항

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| getAnnouncements | GET | /announcements | 공지사항 리스트 조회 | ADMIN |
| createAnnouncementForm | GET | /announcements/form | 공지사항 생성 폼 요청 | ADMIN |
| createAnnouncement | POST | /announcements | 공지사항 생성 요청 | ADMIN |
| announcementDetail | GET | /announcements/{id} | 공지사항 상세 정보 조회 | ADMIN |
| updateAnnouncementForm | GET | /announcements/edit/{id} | 공지사항 수정 폼 요청 | ADMIN |
| updateAnnouncement | PATCH | /announcements/{id} | 공지사항 수정 요청 | ADMIN |
| deleteAnnouncement | Delete | /announcements/{id} | 공지사항 삭제 요청 | ADMIN |

### 📋 채용 공고

| 🏷️ Name | ⚙️ Method | 📎 URL | 📑 Description | 🔨 Authority |
| --- | --- | --- | --- | --- |
| recuit | GET | /recuit | 채용 정보 리스트 조회 | ALL |


## 3. 요구사항 명세
### 3.1 요구사항
### 주요 객체별 요구사항 정의

- **로그인하지 않은 상태에서 접근할 수 있는 페이지**
    - 일반 사용자
        - 로그인 페이지
        - 회원가입 페이지
        - 공지사항-게시판 목록 조회 페이지, 공지사항-게시판 상세 조회 페이지
        - 채용공고 목록 조회 페이지, 채용공고 상세 조회 페이지
    
- **사용자별 권한 등급**
    - 관리자
        - 공지사항-게시판 CRUD, 채용공고 CRUD, 댓글 CURD
    - 일반 사용자
        - 게시판 CRUD, 채용공고 R, 댓글 CRUD
        
- **일반 사용자 회원가입**
    - 입력값 : 이메일(아이디), PW, 이름, 가입 일시, 수정일시
    - 정책 - 생성 (유효성 검사)
        - 아이디는 유효성 검사를 진행하고, 회원가입시 중복 체크를 진행
            - 입력값이 이메일형태인지 확인, 빈 값이 안된다.
            - DB에 해당 아이디가 존재한 상태에서 회원 가입시 ‘이미 존재하는 회원’의 메시지 출력
        - 비밀번호는 최소 8 ~ 16자 이하이며, 영문 대/소문자, 숫자, 특수문자로 구성**(2개 이상을 조합해서 가입)**
        - 이름은 공백 체크만 한 후 길이만 체크 (최소 2자, 최대 6자)
        - 최초 회원가입이 완료되면 가입 일시와 수정 일시 업데이트
        - 회원 수정시 수정 일시만 업데이트
        - 위 정책을 통과하면 회원가입이 완료되고, 로그인 페이지로 이동한다.
        - 최초 회원가입시 일반 사용자의 권한은 ‘일반’이다.
        
- **관리자 회원가입**
    - 입력값 : 이메일(아이디), PW, 이름, 가입 일시, 수정일시
    - 정책 - 생성 (유효성 검사)
        - 아이디는 유효성 검사를 진행하고, 회원가입시 중복 체크를 진행
            - 입력값이 이메일형태인지 확인, 빈 값이 안된다.
            - DB에 해당 아이디가 존재한 상태에서 회원 가입시 ‘이미 존재하는 회원’의 메시지 출력
        - 비밀번호는 최소 8 ~ 16자 이하이며, 영문 대/소문자, 숫자, 특수문자로 구성**(2개 이상을 조합해서 가입)**
        - 이름은 공백 체크만 한 후 길이만 체크 (최소 2자, 최대 6자)
        - 최초 회원가입이 완료되면 가입 일시와 수정 일시 업데이트
        - 회원 수정시 수정 일시만 업데이트
        - 위 정책을 통과하면 회원가입이 완료되고, 로그인 페이지로 이동한다.
        - 최초 회원가입시 관리자는 ‘관리자’ 이다.

- **회원정보 수정**
    - **일반 사용자**
        - 회원정보 수정 가능 항목은 비밀번호, 이름만 가능
        - ID 값을 제외한 나머지 항목 회원가입과 마찬가지로 정책 및 유효성 검사 동일하게 진행
        - 수정이 성공적으로 이뤄지면 해당 유저 데이터의 수정 일시 컬럼을 업데이트
        - 회원 탈퇴도 가능
    - **관리자**
        - 회원정보 수정 가능 항목은 비밀번호, 이름만 가능
        - ID 값을 제외한 나머지 항목 회원가입과 마찬가지로 정책 및 유효성 검사 동일하게 진행
        - 수정이 성공적으로 이뤄지면 해당 유저 데이터의 수정 일시 컬럼을 업데이트
        - 일반 사용자에 대한 정보를 수정할 수 있고, 권한 부여도 가능
        - 회원들을 권한을 관리할 수 있는 관리자 전용 페이지가 존재한다.

- **로그인**
    - **일반 사용자 로그인**
        - 로그인 실패시 “아이디 또는 비밀번호가 일치하지 않는다”의 메시지 출력
        - 로그인 성공시 index 페이지로 이동
    - **관리자 로그인**
        - 로그인 실패시 “아이디 또는 비밀번호가 일치하지 않는다”의 메시지 출력
        - 로그인 성공시 index 페이지로 이동

- 공지사항, 게시판, 채용공고
    - 공통: 게시글, 공지사항 모두 최근 작성한 날짜로 정렬한다. (생성일자)
    - **게시판**
        - 관리자, 일반 사용자 권을 가진 유저만 게시판 글 목록 보기, 글 상세 보기, 글 수정하기, 글 삭제하기, 글 쓰기 가능
        - 게시글 작성, 수정 시 제목과 내용은 공백 혹은 빈칸으로 작성될 수 없음
        - 해당 유저가 작성한 게시글만 수정, 삭제 가능
        - 상세보기 페이지에서 로그인을 하지 않고 글 작성 버튼을 누르는 경우 로그인 페이지로 이동
        - 기본적인 페이징 쿼리, 검색 기능 제공
    
    - **공지사항**
        - 관리자 권한을 가진 유저만 공지사항 글 목록 보기, 글 상세 보기, 글 수정하기, 글 삭제하기, 글 쓰기 가능
        - 공지사항 작성, 수정 시 제목과 내용은 공백 혹은 빈칸으로 작성될 수 없다.
        - 관리자 권한을 가진 유저만이 작성한 공지사항을 수정하거나 삭제할 수 있다.
        - 공지사항에는 댓글을 작성할 수 없다.
        - 게시판 상단에 최신순으로 작성된 공지사항 5개가 노출되어야 한다.
        - 정지된 회원은 공지사항만 볼 수 있다.
        
    - **채용공고**
        - 사람인 API를 호출하여 리스트를 출력해준다.
        - 공고 클릭시 해당 공고 지원 페이지로 이동
        - 기본적인 페이징 쿼리, 검색 기능 제공

- 댓글 작성
    - **기본적인 댓글 기능 구현 (일반 사용자, 관리자, 회사)**
        - 댓글 보기
        - 댓글 수정하기
        - 댓글 수정하기
        - 댓글 작성하기
    - 댓글 작성, 수정 시 내용은 공백 혹은 빈칸으로 작성될 수 없음
    - 해당 유저만 작성한 댓글에 대해 수정, 삭제 가능
    - 댓글을 작성했던 게시글이 삭제될 경우 해당 댓글 데이터도 같이 삭제
    - **게시판에만 댓글 기능을 열어둔다**

### 3.2 추가 요구사항 (추가 구현은 가능한 사람에 한해서만 작업가능, 내용에 크게 반영 X)
- 무중단 배포
- 스프링 시큐리티를 활용한 인증/인가 처리
- Srping Batch를 활용한 매일매일 구인정보 업데이트
  

## 4. 프로젝트 구조와 와이어 프레임 / UI

### 4.1 프로젝트 구조
```
📦src
┣ 📂main
┃ ┣ 📂java
┃ ┃ ┗ 📂com
┃ ┃ ┃ ┗ 📂est
┃ ┃ ┃ ┃ ┗ 📂cranejob
┃ ┃ ┃ ┃ ┃ ┣ 📂announcement
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Announcement.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAnnouncementRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateAnnouncementRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AnnouncementDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AdminUpdateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂comment
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Comment.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateCommentRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateCommentRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateComment.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateComment.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂post
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Post.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreatePostRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdatePostRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostAdminDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostSummaryResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostUserDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreatePost.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdatePost.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂recruit
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Recruit.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitInfo.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HttpURLConnectionEx.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitService.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SchedulerService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂security
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomAuthenticationFailureHandler.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂provider
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FormAuthenticationProvider.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FormUserDetailsService.java
┃ ┃ ┃ ┃ ┃ ┣ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserController.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAdminUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UpdateAdminUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserContext.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserService.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂util
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Role.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserStatus.java
┃ ┃ ┃ ┃ ┃ ┣ 📜CraneJobApplication.java
┃ ┃ ┃ ┃ ┃ ┗ 📜HomeController.java
┃ ┗ 📂resources
┃ ┃ ┣ 📂static
┃ ┃ ┃ ┣ 📂assets
┃ ┃ ┃ ┃ ┣ 📂brand
┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap-logo-white.svg
┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap-logo.svg
┃ ┃ ┃ ┃ ┃ ┗ 📜cranejob-logo-mini.png
┃ ┃ ┃ ┃ ┣ 📂dist
┃ ┃ ┃ ┃ ┃ ┣ 📂css
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap.min.css
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap.min.css.map
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap.rtl.min.css
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap.rtl.min.css.map
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜style.css
┃ ┃ ┃ ┃ ┃ ┗ 📂js
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜bootstrap.bundle.min.js
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜bootstrap.bundle.min.js.map
┃ ┃ ┃ ┃ ┗ 📂js
┃ ┃ ┃ ┃ ┃ ┗ 📜color-modes.js
┃ ┃ ┃ ┗ 📂css
┃ ┃ ┃ ┃ ┣ 📜bootstrap.min.css
┃ ┃ ┃ ┃ ┗ 📜style_userlist.css
┃ ┃ ┣ 📂templates
┃ ┃ ┃ ┣ 📂admin
┃ ┃ ┃ ┃ ┣ 📜edit.html
┃ ┃ ┃ ┃ ┣ 📜login.html
┃ ┃ ┃ ┃ ┣ 📜signup.html
┃ ┃ ┃ ┃ ┣ 📜user-info.html
┃ ┃ ┃ ┃ ┗ 📜user-list.html
┃ ┃ ┃ ┣ 📂announcement
┃ ┃ ┃ ┃ ┣ 📜detail.html
┃ ┃ ┃ ┃ ┣ 📜edit.html
┃ ┃ ┃ ┃ ┣ 📜form.html
┃ ┃ ┃ ┃ ┗ 📜list.html
┃ ┃ ┃ ┣ 📂layout
┃ ┃ ┃ ┃ ┗ 📜header.html
┃ ┃ ┃ ┣ 📂post
┃ ┃ ┃ ┃ ┣ 📜edit.html
┃ ┃ ┃ ┃ ┣ 📜form.html
┃ ┃ ┃ ┃ ┣ 📜list.html
┃ ┃ ┃ ┃ ┗ 📜postDetail.html
┃ ┃ ┃ ┣ 📂recruit
┃ ┃ ┃ ┃ ┗ 📜recruit-list.html
┃ ┃ ┃ ┗ 📂user
┃ ┃ ┃ ┃ ┣ 📜edit.html
┃ ┃ ┃ ┃ ┣ 📜login.html
┃ ┃ ┃ ┃ ┗ 📜signup.html
┃ ┃ ┗ 📜application.yml
┗ 📂test
┃ ┗ 📂java
┃ ┃ ┗ 📂com
┃ ┃ ┃ ┗ 📂est
┃ ┃ ┃ ┃ ┗ 📂cranejob
┃ ┃ ┃ ┃ ┃ ┗ 📜CraneJobApplicationTests.java
```

### 4.2 프로젝트 구조에 대한 설명

- **announcement**: 공지사항 구현에 필요한 controller, service, repository, domain
```
┃ ┃ ┃ ┃ ┃ ┣ 📂announcement
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Announcement.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAnnouncementRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateAnnouncementRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AnnouncementDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AdminUpdateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AnnouncementService.java
```

- **comment**: 댓글 구현에 필요한 controller, service, repository, domain
```
┃ ┃ ┃ ┃ ┃ ┣ 📂comment
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Comment.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateCommentRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateCommentRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateComment.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateComment.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentService.java
```

- **post**: 게시글 구현에 필요한 controller, service, repository, domain
```
┃ ┃ ┃ ┃ ┃ ┣ 📂post
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Post.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreatePostRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdatePostRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostAdminDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PostSummaryResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostUserDetailResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreatePost.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdatePost.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PostService.java
```

- **recurit**: 채용공고 구현에 필요한 controller, service, repository, domain
```
┃ ┃ ┃ ┃ ┃ ┣ 📂recruit
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Recruit.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitInfo.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecruitRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HttpURLConnectionEx.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecruitService.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SchedulerService.java
```
- **security**: 인증/인가 구현에 필요한 Security 파일
```
┃ ┃ ┃ ┃ ┃ ┣ 📂security
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CustomAuthenticationFailureHandler.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂provider
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FormAuthenticationProvider.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FormUserDetailsService.java
```
- **user**: 유저 구현에 필요한 controller, service, repository, domain
```
┃ ┃ ┃ ┃ ┃ ┣ 📂user
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserController.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂dto
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateAdminUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CreateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UpdateAdminUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UpdateUserRequest.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserResponse.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserContext.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂service
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminUserService.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂util
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Role.java
┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserStatus.java
```

- **templates/admin**: 관리자용 회원 관리 페이지를 위한 뷰 템플릿
- **templates/announcement**: 공지사항 페이지를 위한 뷰 템플릿
- **templates/layout**: 공통 레이아웃 템플릿
- **templates/post**: 게시물 페이지를 위한 뷰 템플릿
- **templates/recurit**: 채용공고 페이지를 위한 뷰 템플릿
- **templates/user**: 일반 회원, 관리자 로그인 회원가입을 위한 뷰 템플릿


## 5. 역할 분담
- 팀장 : 이정석 (기획, 설계, 유저 도메인 개발)
- BE : 조아라 (채용공고 도메인 개발, 배포 스크립트 작성)
- BE : 조준호 (DB 설계, 게시물, 댓글 도메인 개발)
- BE : 이소정 (각 도메인에 맞는 뷰 템플릿 개발 및 피그마 작성)
- 디자인 : 이소정

## 6. 와이어프레임 / UI / BM

### 6.1 와이어프레임
<img src="https://github.com/user-attachments/assets/8100b865-16ab-449c-bb29-b1ea052d20da" width="60%">

### 6.2 화면 설계
- 일반 사용자, 관리자가 보는 페이지는 대부분 동일하기 때문에 필요한 부분만 업로드
- 이 후 화면에 대한 내용은 직접 실행해보면서 확인
 
<table>
    <tbody>
        <tr>
            <td>메인</td>
            <td>로그인</td>
        </tr>
        <tr>
            <td>
		<img src="https://github.com/user-attachments/assets/133abba7-6132-4633-94aa-17e7c7c469b9" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/133abba7-6132-4633-94aa-17e7c7c469b9" width="100%">
            </td>
        </tr>
        <tr>
            <td>회원가입</td>
            <td>정보수정</td>
        </tr>
        <tr>
            <td>
                <img src="https://github.com/user-attachments/assets/33ef5478-0b6a-485c-b0ab-a9e58e85b376" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/aaff292f-1b92-4d41-83f5-3e953ac8eb09" width="100%">
            </td>
        </tr>
        <tr>
            <td>게시판</td>
            <td>상세보기 및 댓글</td>
        </tr>
        <tr>
            <td>
                <img src="https://github.com/user-attachments/assets/415fdc40-f6a3-4ae7-8db9-9b1a857fd4f4" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/cb984bde-42ee-4909-8291-e61e52ca70ab" width="100%">
            </td>
        </tr>
        <tr>
            <td>수정하기</td>
            <td>작성하기</td>
        </tr>
        <tr>
            <td>
	        <img src="https://github.com/user-attachments/assets/3340187e-c3fc-4928-8421-6214def1f9bb" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/0b2b011a-eab4-4454-84f5-71ce9e325777" width="100%">
            </td>
        </tr>
        <tr>
            <td>채용공고 게시판</td>
            <td>공지사항 게시판</td>
        </tr>
        <tr>
            <td>
                <img src="https://github.com/user-attachments/assets/8ed34210-3bbf-4ba8-9cf1-458015ebfd0f" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/78287b39-823d-49d1-8129-ad9880d18049" width="100%">
            </td>
        </tr>
        <tr>
            <td>공지사항 상세보기</td>
            <td>공지사항 작성하기</td>
        </tr>
        <tr>
            <td>
                <img src="https://github.com/user-attachments/assets/dc0b60c7-36dd-45a4-a212-cb6a2fb44086" width="100%">
            </td>
            <td>
                <img src="https://github.com/user-attachments/assets/047f744e-baf4-4087-a423-5b4018a3a1ff" width="100%">
            </td>
        </tr>
    </tbody>
</table>

## 7. 데이터베이스 모델링(ERD)

<img width="1234" alt="스크린샷 2024-08-06 오후 3 34 19" src="https://github.com/user-attachments/assets/8cb49b4f-5483-4f4e-a9dd-fa8bdcaaa80e">



## 8. Architecture

* mermaid
```mermaid
graph TD
    A[사용자] -->|브라우저 요청| Nginx
    Nginx -->|트래픽 분산| EC2_1[EC2: 서버 여러대]

    subgraph 프론트엔드
        B[HTML, CSS, Thymeleaf, JavaScript]
    end

    EC2_1 -->|Thymeleaf 통신| B


    subgraph 백엔드
        EC2_1 -->|JPA 통신| RDS[MySQL:RDS]
        EC2_1 -->|인증 요청| C[Security]
        EC2_1 -->|API 호출| E[배치 작업]
    end
```

* PPT
  
<img width="769" alt="스크린샷 2024-08-06 오후 2 28 41" src="https://github.com/user-attachments/assets/dba6681c-8f01-4b17-a4eb-f7e9234ea34d">

