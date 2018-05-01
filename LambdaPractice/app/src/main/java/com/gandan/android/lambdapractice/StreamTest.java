package com.gandan.android.lambdapractice;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by XPS on 2018-05-01.
 */

public class StreamTest {

    List<String> myList = Arrays.asList("test1", "test2", "test3", "test4", "test5", "hello1", "hello2", "hello3");

    @SuppressLint("NewApi")
    public void setMyList(){

        Stream<String> listStream = myList.stream();

        //stream을 사용하는 방법(필터 적용 및 소팅)
        listStream.filter(s -> s.startsWith("h"))
                .sorted().forEach(s -> Log.e("result: ", s+""));

        //forEach만 쓸 경우 .stream()을 생략할 수도 있다!
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
    }
}
