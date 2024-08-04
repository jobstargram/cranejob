# 2차 프로젝트 (CraneJob)
## 1. 목표

### 1.1 목표
- Java, Spring Boot를 이용하여 블로그 서비스 만들기
- 블로그의 주제는 자유

## 2. 개발 환경 
### 2.1 개발 환경
- 개발 언어: HTML, CSS, JavaScript, BootStrap, Java, Spring Boot, MySQL
- 개발 환경: IntelliJ
- 형상관리: Github


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
📦ormi-homework  
┣ 📂hodu  
┃ ┣ 📂css  
┃ ┃ ┣ 📂footer  
┃ ┃ ┣ 📂header  
┃ ┃ ┣ 📂modal  
┃ ┃ ┣ 📂sections  
┃ ┃ ┣ 📂subscribe  
┃ ┃ ┣ 📜common.css  
┃ ┣ 📂img  
┃ ┣ 📂js  
┃ ┣ 📂test-factory  
┃ ┣ 📜index.html  
┗ 📜README.md

### 4.2 프로젝트 구조에 대한 설명

- **hodu/css**: 각 섹션별로 css 파일
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
