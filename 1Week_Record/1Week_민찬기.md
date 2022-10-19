## Title: [1Week] 민찬기

### 미션 요구사항 분석 & 체크리스트

---

[체크리스트 Issue화](https://github.com/likelion-backendschool/FinalProject_MinChanKi_team8/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc)

![image](https://user-images.githubusercontent.com/92210823/196600285-b569c15d-8761-4c4a-94d4-8ad86569b86b.png)

#### 회원
  ✅ 회원가입 (중복검사)
  
  ✅ 로그인 & 로그아웃 (Security)
  
  ✅ 회원정보수정 
  
  (유저 구현 시 nickname에 따른 권한 설정 주의)
  
  ✅ 아이디 & 비밀번호 찾기
  
#### 글

  ✅ CRUD
  
  ✅ HashTag (N:M 맵핑)


### 1주차 미션 요약

---

**[접근 방법]**

#### 기본
- 백엔드 스쿨인만큼, 최소한의 프론트와 최대한의 백엔드를 하려고 노력.
- 테스트 코드 대신 최소한의 프론트를 통한 기능 확인.
- 새로운 디렉토리 구조를 도입(controller -> api, service -> application)
- 한 클래스의 크기가 커지지 않도록 api, application을 세분화

#### 회원
- Security를 최소화 시키려고 노력
- nickname의 유무에 따른 권한 설정에 집중
    
#### 글
- 타임리프 문법에 적응하는데 중점을 둠

#### 해시태그
- n : m 매핑에 유의
- 글 수정 시, 태그가 변경되는 것에 맞추어 해시태그 테이블의 데이터 추가, 삭제에 유의
    
**[특이사항]**

구현 과정에서 아쉬웠던 점 / 궁금했던 점을 정리합니다.

- 프론트를 조금 예쁘게 꾸미고 싶다는 욕심이 있었는데, 시간이 부족해서 아쉬웠다.

- 추후 리팩토링 시, 어떤 부분을 추가적으로 진행하고 싶은지에 대해 구체적으로 작성해주시기 바랍니다.
    - 요청 전용 DTO 중복 감소
    - 메서드 단일 책임
    - application과 util의 분리
    - 프론트 css
