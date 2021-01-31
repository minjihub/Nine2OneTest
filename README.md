# Nine2OneTest
Open API "Kakao 로컬 > 키워드로 검색"을 사용해 장소를 검색하는 앱 개발

<br>

## 언어
- Kotlin
<br>

## 라이브러리
- Retrofit2
<br>

## 검색 로직
검색어 입력 후 0.5초 뒤 자동 검색
 - EditText의 입력이 끝나면 Handler postDelayed를 사용해 0.5초 뒤 검색

<br>
 
검색 결과 10개씩 페이지네이션 처리
 - 스크롤 시 마지막 포지션이 리스트 총 카운트에 도달했을 때 다음 페이지를 호출하여 리스트에 추가시킨 뒤 값을 갱신하도록 처리
 
<br>
 
검색 결과 tile에 API 응답의 place_name, address_name, distance 포함
 - distance(중심좌표까지의 거리)를 얻기 위해 현 위치의 x,y 좌표를 구한 뒤 값 전송
 - 현 위치 추적을 허용시킬 퍼미션 요청 처리

<br>
 
검색 결과 클릭 시 해당 결과의 place_url을 로드하는 웹뷰로 이동
 - place_url이 null일 경우 웹뷰를 finish하여 이동이 안되는 것처럼 보이게 처리
 
 

<br>

## 결과
1. 위치 퍼미션 요청
<img src="https://user-images.githubusercontent.com/70570798/106389689-fe6d2500-6427-11eb-885e-ae314bd44657.jpg"  width="350" height="600">
<br />

2. 메인페이지
<img src="https://user-images.githubusercontent.com/70570798/106390809-94577e80-642d-11eb-95f8-94cf98eec09a.jpg"  width="350" height="600">
<br />

3. 아이템 클릭 시 웹뷰로 이동
<img src="https://user-images.githubusercontent.com/70570798/106390832-a6d1b800-642d-11eb-8e58-f96cf0838bda.jpg"  width="350" height="600">
