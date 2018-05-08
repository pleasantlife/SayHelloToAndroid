package com.gandan.android.lambdapractice;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
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

        //배열의 일부에서 스트림을 생성할 수 있다.
        Stream<String> partStream = Arrays.stream(helloList, 0, 3);

        //스트림의 요소가 한 개도 없는 '빈 스트림'을 생성할 수도 있다.
        Stream emptyStream = Stream.empty();

        //무한 스트림을 생성할 수도 있다.
        Stream<Double> infiniteStream = Stream.generate(Math::random);


        //Stream의 메서드

        //filter 메서드 : 새로운 스트림을 반환
        partStream.filter(s -> s.startsWith("H"));

        //map 메서드 : Stream에 있는 값들을 특정방식으로 변환하여 새로운 스트림을 반환
        Stream<Character> mapStream = sortStream.map(s -> s.charAt(0));

        //flatMap 메서드 : 여러 스트림들을 합쳐서 하나의 새로운 스트림을 반환
        Stream<Character> flatStream = distinctStream.flatMap(s -> characterStream(s));


        //서브스트림

        //n개 요소 이후 끝나는 새로운 스트림을 반환
        //31번째 부터 스트림으로 반환하는 예
        Stream<Double> afterStream = Stream.generate(Math::random).limit(30);

        //n개 요소를 버린 후 이어지는 스트림을 반환
        Stream<String> skipStream = partStream.skip(0);

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
        //range()는 마지막 수가 범위에 미포함, rangeClosed()는 마지막 수가 범위에 포함.
        IntStream.rangeClosed(1,10);

        //무한 스트림 생성.
        IntStream intsStream = new Random().ints();
        LongStream longlongStream = new Random().longs();
        DoubleStream doubledoubleStream = new Random().doubles();

        //유한 스트림 생성(LongStream, DoubleStream 모두 적용)
        IntStream intLimitStream = new Random().ints(100);
        IntStream intRangeStream = new Random().ints(200, 0, 100);
        IntStream rangeStream = new Random().ints(0, 100);
    }

    private Stream<Character> characterStream(String s){
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()){
            result.add(c);
        }
        return result.stream();
    }
}
