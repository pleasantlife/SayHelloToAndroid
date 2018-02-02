# Dagger2 라이트하게 훑어보기
 - 강연일시 : 2018년 2월 2일
 - 강연장소 : '2018 Lightning Talk'(2018년 2월 2일 GDG Korea 주최)
 - 강연자 : 드라마 앤 컴퍼니 이승민님

## Dagger를 위한 기본개념 이해하기

### 1. Dagger의 정의
 - Dagger는 의존성 주입(Dependencies Injection) 프레임워크이다.

### 1) 의존성 주입은 무엇인가?
 - 의존성 주입이란 구성요소간의 의존 관계가 소스코드 내부가 아닌 외부의 설정파일 등을 통해 정의되게 하는 디자인 패턴 중의 하나이다.(by 위키피디아)
 - IOC(Inversion of Control, 제어 역전)을 구현하기 위한 방법 중 하나이다. 
 - 의존성 주입의 예시 : A 클래스가 B 클래스를 의존할 때, B 오브젝트 생성을 A 클래스 대신 외부에서 생성하여 넘겨주었다.
 - 또 다른 의존성 주입의 형태는 자바 스프링이 있다.

### 2) 그럼 의존성 주입은 왜 필요한가?
 - 객체 생성을 외부에 위임한다. -> Constructor Parameter 등의 보일러 플레이트 코드를 제거할 수 있다.
 - 보일러 플레이트 코드를 제거함으로서 코드를 유연하고 편하게 변경할 수 있다.

## 2. Dagger의 주요개념 이해하기
 - Dagger가 갖고 있는 중요한 개념들 : Inject, Component, Subcomponent, Module, Scope
  1) Inject : Component에게 의존성 주입을 요청.
  2) Component : Module로부터 생성된 의존성 객체를 주입.
  3) SubComponent : 부모 Component의 Inner 컴포넌트. SubComponent의 모듈로 주입이 실패하면, 부모 Component 모듈을 검색. (안드로이드에서는 화면의 상속구조가 갖춰져 있는데, 이를 이용해 Dagger와 일대일로 매칭할 수 있다.)
  4) Module : 의존성 객체를 생성
  5) Scope : 생성된 객체의 Lifecycle 범위

### 3) Dagger의 주요개념에 따른 흐름도
 - 흐름도1 : SubComponent에 Object가 있을 때
 <pre>
 Inject -> SubComponent -> Module -> Scope에 있으면 return / 없으면 생성</pre>
 - 흐름도 2 : Subcomponent에 Object가 없을 때
 <pre>
 Inject-> Subcomponent -> Object 없음 -> 상위 컴포넌트 -> Module검색 -> Scope에 있으면 return / 없으면 생성 -> 어느곳에서도 찾지 못하면 컴파일 에러.</pre>

## 3. Dagger의 추가 개념
 - Inject 생성자 : 생성자 자체에도 Inject 어노테이션이 쓰일 수 있다.
 - 테스트 객체 : 테스트 객체, 실제 객체를 쉽게 나눌 수 있다.

## 4. 마무리
 - Dagger는 러닝커브가 높지만, 장점이 크다.
 - 한번에 하려고 하면 어려운 만큼, 호흡을 길게 두고 공부하는 것이 좋다.
 - 참고사이트 : [Dagger공식사이트(https://google.github.io/dagger/)](https://google.github.io/dagger/) 

## 5. Q&A
 - Q1 : 생성되는 타이밍을 컨트롤 할 수 있는지 궁금하다.
 > A1 : Lazy Class 안에 주입받고 싶은 객체를 넣으면, Lazy Class안에 객체.get()을 하면 주입을 요구함.
 - Q2 : DI/BI라는 개념이 서버쪽에서는 친숙하고, 많이 사용하는 개념이다. 하지만, 안드로이드 개발할 때 DI를 쓰면 어떤 장점을 갖는지 궁금하다.
 > A2 : 파라미터의 변경, 클래스 명세의 변경 등에 신속하고 편하게 대응할 수 있다.