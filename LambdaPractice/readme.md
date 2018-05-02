# 안드로이드에서 람다식 사용해보기 + Stream 이해하기

## 1. 람다식이란?
 - 프로그래밍 언어에서 '익명 함수'를 지칭하는 개념. 람다 함수라고도 한다.
 - 고차 함수에 인자(argument)로 전달되거나 고차 함수의 return 값으로 쓰인다.

## 2. 람다식의 장점?
 - 코드가 간결해진다 : 불필요한 코드가 삭제되어, 그만큼 코드의 양이 줄어든다. => 가독성이 향상된다.

## 3. 안드로이드 스튜디오에서의 람다
 - 안드로이드 스튜디오 3.0 이전에는 RetroLambda라는 라이브러리를 이용해야 람다식을 쓸 수 있다.
 - 3.0 버전부터는 Java8을 지원하여, 별도의 라이브러리 없이 람다식을 사용할 수 있다.

## 4. 간단한 람다식 만들어보기
```java
Button btn = new Button();
btn.setOnClickListener( v -> Log.e("ButtonTest", "It's OK!") );
```

## 5. Stream이란?
 - 자바에 함수형 프로그래밍을 사용하기 위한 API.
 - 단일/멀티 쓰레드를 사용해 원본 데이터를 가지고 검색(필터링), 정렬(소팅), 연산 등 다양한 동작을 수행할 수 있다.
 - 본래 객체 지향형 언어인 Java가 람다와 스트림을 더해 함수형 프로그래밍이 가능해지게 되었음.
 - Java 1.8버전부터 도입됨.
 - 단, Stream에서 사용하는 함수는 원본 데이터를 수정하지 않는다. 따라서, 사용하기 전에 원본데이터를 확보할 필요가 있다.

### 5-1. Stream의 장점?
 - Iterator를 이용하지 않아도 된다.
    1) 보통 Iterator를 이용하여 배열, 리스트 맵 등의 집합체 내부의 개별원소(값)들을 빼낸 후 원하는 연산을 한다.
    2) 하지만 Stream은 개별원소들을 빼내지 않아도 원본 데이터(집합체)에서 바로 연산을 수행하게 된다.
    3) 따라서 for문이나 if문등으로 길어진 코드를 상당히 줄일 수 있다.
 - 함수형 프로그래밍이 가능하다.

### 5-2. Stream 기본 사용법
 - Stream은 원소들을 나타내고, 각 원소들간의 연산을 수행할 수 있도록 다양한 메소드를 가지고 있다.
```java
// Stream 연산을 할 리스트 선언.
private void setStream(){
    List<String> helloList = Arrays.asList("Annyeong", "Hello", "Koniziwa", "Gutentak!", "Bonjour", "Hola");
    
    //Stream 선언.(단일스레드)
    Stream helloStream = helloList.stream();

    //IntStream, LongStream, DoubleStream이 원시 자료형을 위해 따로 마련되어 있다.
    IntStream.range(0, 30).forEach(System.out::println);
    
    //'h'로 시작하는 원소 찾기
    helloStream.filter(s -> s.startWith("h")).forEach(s -> System.out.println("result : " + s));

    //Stream 선언.(멀티스레드)
    Stream parallelHelloStream = helloList.parallelStream();

    //멀티스레드에 대한 결과값 찍어 보여주기.
    parallelHelloStream.forEach(s -> System.out.println("parallelResult : " + s));
}
```

### 5-3. Stream 사용 시 유의사항
 - Stream에서의 ForEach는 Loop가 아니다. : break;나 return;이 동작하지 않고 Stream을 발동시킨 리스트의 사이즈만큼 무조건 연산한다.
 - Stream 객체는 재사용이 불가능 하다. : Stream 객체를 선언한 후 한번 사용하고나서, 선언한 객체를 다시 사용하면 'java.lang.IllegalStateException: stream has already been operated upon or closed' 에러가 난다.
