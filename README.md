# Neorangnarang

JAVA Spring Boot, JPA, MySQL, javascript axios(비동기 통신)을 통한 간단한 페이지 제작

- Rest API를 활용하고자 하였습니다.
- MySql로 DB 데이터 관리
- Spring boot 프로젝트
- Spring security 사용
- JPA를 활용하여 테이블 생성 및 CRUD 처리
- Mapper를 활용하여 CRUD 처리
- Hikari-DB 활용
- Axios를 통하여 비동기 통신
- Bootstrap을 활용하여 (일부) UI작업

---

## 목록

- board
  - list
  - 글쓰기
  - 자세히 보기
  - 수정
  - 삭제
  - 댓글(게시글 당 1:n 매칭)
    - 댓글 쓰기
    - 추가 불러오기
    - 삭제
    - 수정
- 회원 정보
  - 회원 가입
  - 로그인
  - 로그아웃
  - 회원 정보 보기
  - 작성한 글/댓글 보기

---

### 게시글 목록

![image](https://user-images.githubusercontent.com/112995137/210193917-df10f22f-cd64-4b91-bc33-f19d0c92d367.png)

- +버튼을 누를 경우 게시글 쓰기 기능
- 리스트가 추가되면서 하단의 불러오기 버튼으로 페이징

### 판매글 등록하기

![image](https://user-images.githubusercontent.com/112995137/210193950-d87c201f-939b-490f-972e-025ea820baa9.png)

- 한 게시글당 이미지는 최대 5개 까지 등록 가능(삭제 불가능)
  - 드래그 앤 드롭으로 이미지 추가 할 수 있도록 함
  - 이미지는 추가 시점에서 서버에 저장하며 오류가 발생할 경우 삭제 처리
- 제목, 가격, 카테고리, 내용으로 간략화

### 게시글 내용

![image](https://user-images.githubusercontent.com/112995137/210193959-8c66c825-62a0-4433-b521-58a60ee81250.png)

- 등록한 이미지, 판매 여부, 가격 등의 내용 자세히 보기
  - 이미지는 가로로 슬라이드 되도록 처리
- 글 작성자만 수정 및 삭제 가능

### 댓글

![image](https://user-images.githubusercontent.com/112995137/210193972-f6505070-18a6-4dfa-b224-82bfb6544326.png)

- 추가, 삭제, 수정 가능

![image](https://user-images.githubusercontent.com/112995137/210194034-abe7ce7a-e8b4-434c-bbf7-f9f32a453e89.png)

- 본인의 글 및 댓글이 아닐 경우 수정 및 삭제가 불가능하도록 처리

---

### 회원 가입

![image](https://user-images.githubusercontent.com/112995137/210193996-195859c8-59c4-4462-9413-ab15d4114d15.png)

### 로그인

![image](https://user-images.githubusercontent.com/112995137/210194006-22b4bb6e-e1bf-4aa0-90be-5abea42764cc.png)
