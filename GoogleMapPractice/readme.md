# Google map API 이용하기!

## 1. 구글맵 액티비티 생성하기
- 안드로이드 스튜디오에서 'Google Maps Activity' 선택
- 자동으로 구글맵 액티비티 생성!
- 생성시 기본적으로 내장된 '맵프래그먼트' 사용
- 오버라이드 된 0nMapReady 메소드에서 맵 시작시 필요한 사항들을 세팅

## 2. 지도의 중심점 잡기
1. onMapReady 메소드에서 LatLng 객체를 새로 생성하고,
객체의 파라미터로 경도, 위도를 넣는다.
2. 생성한 LatLng 객체를 중심점으로 담기 위한 CameraPosition.Builder로 CameraPosition 객체를 생성한다. (이 때 1번에서 생성한 LatLng 객체를 담는다.)
3. 생성한 CameraPosition 객체를 구글맵의 moveCamera메소드에 파라미터로 넣는다.

```java
//중심점 잡기
LatLng latLng = new LatLng(37.497082, 127.028704);

//카메라 위치 잡기
Cameraposition cameraPosition = CameraPosition.builder().target(latLng).build();

//카메라 위치 대입하기
map.moveCamera(CameraUpdateFacotry.newCameraPosition(cameraPosition));
```

## 3. 줌 레벨 설정하기
1. 지도 생성시에는 오버라이드된 onMapReady에서 zoom 레벨을 설정해야 한다.
2. 이를 위해, CameraPostion.Builder에서 zoom 메소드를 이용한다.

### 줌 레벨
 1 : 가장 축소된 레벨. 세계단위.
 5 : 대륙 단위
 10: 도시 단위
 15: 거리 단위
 20: 가장 확대된 레벨. 건물 단위

```java
//중심점 잡기
LatLng latLng = new LatLng(37.497082, 127.028704);

//카메라 위치 잡기

Cameraposition cameraPosition = CameraPosition.builder().target(latLng).zoom(15).build();
```
