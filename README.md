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

## 트러블 슈팅
Kakao API 통신 부분을 완료한 뒤, 페이지네이션 처리 로직을 작성했는데 처음엔 어떻게 해야할지 큰 로직이 그려지지 않았습니다.  <br>
'전체 데이터를 API에서 불러오고 10개씩 나눠서 RecyclerView에 보여줘야 하는 것인가'라고 생각했는데, 전체 데이터를 갖고 있는다는 부담이 있을 뿐더러 처음 불러왔을 때 15개의 값만 불러오고 있었습니다. <br>
<br>
이 부분이 이상하다고 생각이 들어 API 문서를 다시 살펴보니 요청URL의 파라미터로 결과페이지 번호라는 'page'는 기본값이 1이었고, 한 페이지에 보여줄 문서의 개수인 'size'는 기본값이 15였기때문에
1페이지의 15개의 값만 보여주고 있었습니다. <br>
<br>
이를 참고하여 size는 10으로 변경하고, 스크롤이 맨 밑으로 내려갔을 때 그 다음 페이지의 값을 요청하여 결과 값을 RecyclerView List에 추가해주는 방식으로 로직을 그렸습니다. <br>
그 결과, 마지막 스크롤의 포지션을 알 수 있는 findLastCompletelyVisibleItemPosition 이라는 메소드가 존재하여 이것과 총 리스트의 수를 비교하고 
같을 경우 다음 페이지를 로드하여 리스트에 추가한 뒤 notifyDataSetChanged를 하도록 만들었습니다.


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
