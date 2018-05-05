package com.gandan.android.lambdapractice;

/**
 * 변수에 함수를 담아두기 위한 인터페이스.
 */

//인터페이스 하나에 한 개의 메소드만 쓸 수 있도록 함.
@FunctionalInterface
public interface Calculate {
    public int calc(int a, int b);
}
