# 안드로이드에서의 인터페이스

## 1. 인터페이스 기본
- 자바에서는 보통 '다중 구현(implements)'를 할 때 인터페이스를 사용한다. (하나의 클래스만 상속받을 수 있으므로)
- 또한, 규칙의 개념을 가지고 있어 상호간 동일한 구현이 필요한 클래스들이 있을 때 사용한다.

## 2. 안드로이드에서의 인터페이스
- 안드로이드에서는 위의 기능에 추가해 '콜백이 필요할 때' 인터페이스를 사용하기도 한다.
- 버튼에 붙는 onClickListener 등 Listener가 붙는 것들이 바로 인터페이스이다.
```java
Button button = new Button();
//콜백을 위해 인터페이스 사용
button.setOnClickListener(new View.OnClickListener() {
    //인터페이스에 속한 메소드 구현
    @Override
    public void onClick(View v){
        Toast.makeText(this, "테스트", Toast.LENGTH_SHORT).show();
    }
});
```
- 리사이클러뷰 아이템이나 프래그먼트에서 인터페이스를 사용하면 액티비티로 쉽게 데이터를 넘겨줄 수 있다.
