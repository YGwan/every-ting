# CODING CONVENTIONS

## 주석
- 라이브러리, 유틸 클래스, API는 Java Doc 수준의 상세한 주석을 필수로 한다.
- 주석은 코드 위 또는 오른쪽에 작성한다.
  아래 작성하는 것을 지양한다.

## 코드
- @Autowired는 생성자 주입으로 개선한다.
-  Setter와 @Data 사용을 지양한다. Factory Method 형태로 작성하고 @Builder는 private 생성자로 등록한다.
- 하드코딩은 지양하고, 대상 문구들은 상수로 관리한다.
- from 은 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드에 사용
- of 는 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드에 사용

## 네이밍
- 명확한 의미 전달을 위해, 축약형 사용을 지양한다.
- 메서드명과 변수 명은 용도에 맞게 작성한다.
- 상수는 대문자+언더스코어 조합, 변수는 CamelCase 형식으로 작성한다.


## Controller
- 컨트롤러 클래스 안에서 메서드 명을 작성 할 때는 아래와 같은 접미사를 붙인다.
    - orderList() : 목록 조회 유형의 서비스
    - orderDetails() : 단 건 상세 조회 유형의 controller 메서드
    - orderSave() : 등록/수정/삭제 가 동시에 일어나는 유형의 controller 메서드
    - orderAdd() : 등록만 하는 유형의 controller 메서드
    - orderModify() : 수정만 하는 유형의 controller 메서드
    - orderRemove() : 삭제만 하는 유형의 controller 메서드

<br>

## Service
- 서비스 클래스 안에서 메서드 명을 작성 할 때는 아래와 같은 접두사를 붙인다.
    - findOrder() : 조회 유형의 service 메서드
    - addOrder() : 등록 유형의 service 메서드
    - modifyOrder() : 변경 유형의 service 메서드
    - removeOrder() : 삭제 유형의 service 메서드
    - saveOrder() : 등록/수정/삭제 가 동시에 일어나는 유형의 service 메서드

<br>

## Structure
1. 패키지는 목적별로 묶어 생성한다.
    - common(공통기능 관련), user(유저 관련), Order(주문관련) ....

2. Controller에서는 Service 호출과 Exception 처리만을 담당한다.
    - Controller에서의 비즈니스 로직 구현은 최대한 피한다.

3. 메소드와 클래스는 하나의 목적만을 위해 생성한다.
    - 한개의 메소드는 한가지의 기능만을 가져야 한다.
    - 한개의 클래스 내부에는 같은 목적만을 가진 코드가 존재하여야한다.

4. 메소드와 클래스는 가능한 작게만든다.
    - 여러 기능이 모인 적은수의 큰 클래스보다는 목적이 뚜렷한 작은 클래스 여러개로 이루어진 시스템이 바람직하다.

5. 도메인명의 Service 생성은 피한다.
    - Order 라는 도메인이 있을 때 OrderService 로 만드는 것은 피한다.
    - OrderService로 만들 경우 그 안에 도메인과 관련된 여러 기능을 넣을 가능성이 높다.
    - 도메인 관련 기능을 세분화하여 Service를 만든다(ex. OrderRegisretService, OrderStatusService .....)


<br><br><br><br><br>