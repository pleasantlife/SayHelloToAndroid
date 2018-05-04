package com.gandan.android.lambdapractice;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by XPS on 2018-05-01.
 */

public class StreamTest {

    List<String> myList = Arrays.asList("test1", "test2", "test3", "test4", "test5", "hello1", "hello2", "hello3");
    String[] helloList = {"Welcome", "환영합니다.", "Willkommen", "Hola"};

    private void sayHello(String text){
        Log.e("Say Hello : ", text+"");
    }

    @SuppressLint("NewApi")
    public void setMyList(){

        //Stream을 생성하는 다양한 방법.
        Stream<String> listStream = myList.stream();
        Stream<String> helloStream = Stream.of(helloList);
        //Stream<String> byeStream = Stream.of(new String[]{"안녕","bye"});
        Stream<String> byeStream = Stream.of("안녕", "bye");

        //Stream 중간 연산 종류 (일부)
        //필터링 (조건에 맞는 요소만 뽑아낸다.)
        Stream<String> filterStream = helloStream.filter(s -> s.contains("W"));
        //중복제거
        Stream<String> distinctStream = helloStream.distinct();
        //정렬
        Stream<String> sortStream = helloStream.sorted();
        //갯수에 맞게 자르기
        Stream<String> cutStream = helloStream.limit(1);
        //스트림의 요소에 작업을 수행함.
        Stream<String> peekStream = helloStream.peek(this::sayHello);
        //스트림의 요소가 한 개도 없는 '빈 스트림'을 생성할 수도 있다.
        Stream emptyStream = Stream.empty();
        //두 스트림을 하나로 연결할 수도 있다.(단, 두 요소는 같은 타입이어야 한다.)
        Stream<String> conStream = Stream.concat(listStream, helloStream);

        //stream을 사용하는 방법(필터 적용 및 소팅)
        listStream.filter(s -> s.startsWith("h"))
                .sorted().forEach(s -> Log.e("result: ", s+""));

        myList.forEach(s -> Log.e("myListItem", s + ""));

        //주의사항 : Stream은 재사용이 불가능하다!
        //재사용시 java.lang.IllegalStateException: stream has already been operated upon or closed 에러가 나온다.

        //parallelStream()으로 병렬사용도 가능하다! (멀티 스레드)
        Stream<String> parallelStreams = myList.parallelStream();
        parallelStreams.forEach(s -> Log.e("parallelStream", Thread.currentThread().getName()+""));

        //Optional을 통해 null에 대한 방어도 가능.
        Optional<String> result = myList.stream().filter(s -> s.startsWith("test")).findFirst();

        //isPresent()를 통해 null인지 아닌지 알 수 있음.
        if(result.isPresent()){
            Log.e("result Optional", result.get()+"");
        }

        //위 Optional Stream을 람다로 줄인 형태.
        result.ifPresent(s -> Log.e("result Optional Lambda", result.get()+""));

        //원시 자료형 중 숫자를 연산할 수 있는 IntStream, DoubleStream, LongStream이 따로 존재한다.
        IntStream.range(1, 10).forEach(i -> Log.e("intStream : ", i+""));
    }
}
