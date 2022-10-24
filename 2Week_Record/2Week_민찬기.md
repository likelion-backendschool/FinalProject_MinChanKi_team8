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


### 2주차 미션 요약

---

**[접근 방법]**

#### 기본
- 관심사 중심의 분리를 유지하려 노력
- 
    
**[특이사항]**

구현 과정에서 아쉬웠던 점 / 궁금했던 점을 정리합니다.

- 프론트를 조금 예쁘게 꾸미고 싶다는 욕심이 있었는데, 시간이 부족해서 아쉬웠다.

- 추후 리팩토링 시, 어떤 부분을 추가적으로 진행하고 싶은지에 대해 구체적으로 작성해주시기 바랍니다.
    - Querydsl
    - ExceptionHandler -> Errorpage -> message를 객체로 던져줘서 처리할 수 있도록
    - test
    - CreateDate, UpdateDate 중복 제거를 위한 BaseEntity 생성
