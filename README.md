# 2차 프로젝트 (CraneJob)
## 1. 목표

### 1.1 목표
- Java, Spring Boot를 이용하여 블로그 서비스 만들기
- 블로그의 주제는 자유

### 1.2 기능
- 자사 콘텐츠와 연계가 가능한 플랫폼
- 이력 관리가 가능한 플랫폼(Github Open API 활용)
- 역량별 레벨 관리 시스템 제공(Github Open API 활용, 스터디 그룹 결과물)
- SW 관련 해커톤이나 워크숍, 세미나, 밋업 등이 자유롭게 공유될 수 있는 플랫폼
- 타 플랫폼에서도 활용할 수 있는 API Set 제공
- 각 언어별 로그인 없이 실습이 바로 가능한 환경(google colab의 경우 학생들 이름으로 가입되어 있으면 작동하지 않음)

### 1.3 팀 구성
- 실제 사진을 업로드 하시길 권합니다.
<table>
	<tr>
		<th>이정석</th>
		<th>조아라</th>
		<th>조준호</th>
		<th>이소정B</th>
	</tr>
 	<tr>
		<td><img src="my.jpg" width="100%"></td>
		<td><img src="my.jpg" width="100%"></td>
		<td><img src="my.jpg" width="100%"></td>
		<td><img src="my.jpg" width="100%"></td>
	</tr>
</table>

## 2. 개발 환경 
### 2.1 개발 환경
- 개발 언어: HTML, CSS, JavaScript, BootStrap, Java, Spring Boot, MySQL
- 개발 환경: IntelliJ
- 형상관리: Github

### 2.2 배포 URL
- https://www.studyin.co.kr/
- 테스트용 계정
  ```
  id : test@test.test
  pw : test11!!
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
- HTML, CSS를 이용한 레이아웃 구현
- JS 기능과 관련된 부분인 Show more 버튼을 클릭했을 때의 추가적인 이미지 렌더링
- kakao map api를 사용하여 지도 부분 구현
- Subscribe 버튼 클릭 시 고양이 모달 렌더링

### 3.2 추가 요구사항 (추가 구현은 가능한 사람에 한해서만 작업가능, 내용에 크게 반영 X)
- 반응형 레이아웃
- 필요에 의해 디자인, 레이아웃 변경 가능
  

## 4. 프로젝트 구조와 와이어 프레임 / UI

### 4.1 프로젝트 구조
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

### 4.2 프로젝트 구조에 대한 설명

- **announcement**: 공지사항 구현에 필요한 controller, service, repository, domain
- **hodu/img**: 레이아웃 구현에 필요한 이미지 파일
- **hodu/js**: 모달창, show-more 버튼을 위한 JS 파일
- **hodu/test-factory**: 테스트용 파일
- **index.html**: 실제 디자인이 완성된 index 파일
- **README.md**: 프로젝트 구조와 설명을 포함한 파일


### 4.3 와이어프레임
<img width="912" alt="스크린샷 2024-05-27 오후 1 21 51" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/28fa8500-9721-4e13-9f35-e7a88dc8fae7">


### 4.3 화면 설계
 
<table>
    <tbody>
        <tr>
            <td>헤더</td>
            <td>메인</td>
        </tr>
        <tr>
            <td>
		<img width="100%" alt="스크린샷 2024-05-27 오후 1 23 57" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/8635d971-5fdc-4bed-bd66-ab4ac1fc93bb">
            </td>
            <td>
                <img width="100%" alt="스크린샷 2024-05-27 오후 1 27 53" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/9ef12597-511b-473d-94a1-f1a3808e5bba">
            </td>
        </tr>
        <tr>
            <td>본문</td>
            <td>본문 갤러리</td>
        </tr>
        <tr>
            <td>
                <img width="100%" alt="스크린샷 2024-05-27 오후 1 25 40" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/cc8ac302-1bab-4511-91e2-4ab831b1f86e">
            </td>
            <td>
                <img width="100%" alt="스크린샷 2024-05-27 오후 1 26 20" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/7f46a815-f8b0-434a-96b7-239d98152372">
            </td>
        </tr>
        <tr>
            <td>지도</td>
            <td>하단영역</td>
        </tr>
        <tr>
            <td>
                <img width="100%" alt="스크린샷 2024-05-27 오후 1 26 42" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/2b929b38-71ac-492d-a76d-fc2c85cd04bb">
            </td>
            <td>
                <img width="812" alt="스크린샷 2024-05-27 오후 1 27 07" src="https://github.com/LeeJeongSeok/ormi-homework/assets/13554850/f74fbc43-d09f-4c71-9c93-4e11c72572b4">
            </td>
        </tr>
    </tbody>
</table>


## 5. 개발하면서 학습한 내용과 느낀점
### 5.1 HTML 시멘틱 태그
- 학습 내용: 시멘틱 태그를 통해 HTML 문서의 구조를 보다 명확하게 표현하는 방법을 배웠다.
- 느낀 점: 기존에는 div와 span만으로 모든 레이아웃을 구성했지만, 시멘틱 태그를 사용하니 코드의 가독성이 크게 향상되었다. 예를 들어, header, main, footer 등을 사용하니 문서 구조가 직관적으로 이해되었다.

### 5.2 CSS 선택자와 레이아웃
- 학습 내용: CSS 선택자를 활용하여 다양한 요소를 스타일링하고, Flex를 사용하여 레이아웃을 구성하는 방법을 익혔다.
- 느낀 점: 선택자 우선순위와 박스 모델의 이해가 중요하다는 것을 깨달았다. 특히 Flex를 사용하니 수평 및 수직 정렬이 간편해졌고, 다양한 속성들을 통해 복잡한 레이아웃도 손쉽게 구현할 수 있었다. 하지만 아직까지 익숙하지않아 주먹구구식으로 개발했다.

### 5.3 JavaScript 비동기 통신
- 학습 내용: Fetch API를 이용한 비동기 통신 방법을 학습하고, 비동기 작업을 효율적으로 처리하기 위한 방법을 익혔다.
- 느낀 점: 처음에는 비동기 통신 개념이 다소 어려웠으나, Promises와 async/await 문법을 사용하니 코드가 깔끔해지고 이해하기 쉬워졌다. 실제로 Show more 버튼을 구현하면서, 추가적인 이미지 로딩이 자연스럽게 이루어지는 것을 보며 큰 성취감을 느꼈다.
