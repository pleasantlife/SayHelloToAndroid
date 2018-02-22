# 디데이 카운터 앱 만들어보기!

## 1. Calendar 클래스 이용
 - 자바에서 날짜와 시간에 관련된 데이터를 쉽게 처리할 수 있도록 제공하는 클래스.
 - Date도 있지만, Calendar가 더욱 향상된 기능을 제공하니, 가능하면 Date보다는 Calendar를 사용하는게 좋음.

## 2. Calendar에 사용자가 지정한 날짜 입력하기
 1) Calendar 클래스 변수를 초기화한다. 이때, Calendar.getInstance()를 변수에 대입하여 생성한다.
 2) 입력받은 연, 월, 일을 각각 int 값으로 변환하여 Calendar에 세팅한다.
 3) 월의 경우 Calendar에서는 0월~11월로 인식하고 있으므로, 월을 세팅할 때에는 1을 빼야한다. 
 ```java
 Calendar setCalendar = Calendar.getInstance();
 
 int inputYear = Integer.parseInt(stringYear.getText().toString());
 int inputMonth = Integer.parseInt(stringMonth.getText().toString());
 int inputDay = Integer.parseInt(stringDay.getText().toString());
 
 setCalendar.set(inputYear, inputMonth-1, inputDay);
 ```

## 3. D-Day 계산하기
 1) 2.에서 입력한 calendar값은 getTimeInMillis()를 통해 long값으로 변환할 수 있다.
 2) 현재시각(System.currentTimeMillis)에서 calendar의 long값을 뺀다.
 ```java
 long dDayLong = System.currentTimeMillis() - setCalendar.getTimeInMillis();
 ``` 