## Title: [2Week] 민찬기

### 미션 요구사항 분석 & 체크리스트

---

#### 상품
<img width="622" alt="image" src="https://user-images.githubusercontent.com/92210823/197576594-a4e93a32-f373-45ad-8950-2aa44ac3296d.png">

#### 장바구니
<img width="622" alt="image" src="https://user-images.githubusercontent.com/92210823/197576678-311bc678-dd61-4f64-8127-30d90eb15571.png">

#### 주문 환불
<img width="622" alt="image" src="https://user-images.githubusercontent.com/92210823/197963691-9b0596c0-9fbc-490f-b68c-baf7f23db5a8.png">




### 2주차 미션 요약

---

**[접근 방법]**

#### 기본
- 관심사 중심의 분리를 유지하려 노력
- issue를 꼼꼼하게 작성하려 노력
- service에서 service 호출을 하지 않으려 함
- 전체적인 리팩토링에 앞서, 건드는 부분에 대한 리팩토링
    - BaseEntity, BaseParam
    
**[특이사항]**

구현 과정에서 아쉬웠던 점 / 궁금했던 점을 정리합니다.

- view layout 적용을 시도 했는데 시간 부족
- 서비스 로직에 대한 정리 부족
  - 충전 시점에 대한 정의 필요

- 추후 리팩토링 시, 어떤 부분을 추가적으로 진행하고 싶은지에 대해 구체적으로 작성해주시기 바랍니다.
    - Querydsl
    - ExceptionHandler -> Errorpage -> message를 객체로 던져줘서 처리할 수 있도록
    - CreateDate, UpdateDate 중복 제거를 위한 BaseEntity 생성
    - view에 layout 적용
