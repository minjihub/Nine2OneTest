package com.parkminji.nine2onetest.model

data class PlaceData(val documents: List<Place>)

data class Place(val place_name: String?, // 장소명
                 val address_name: String?, // 전체 지번 주소
                 val distance: String?, // 중심좌표까지의 거리 (단위 meter)
                 val place_url: String?) // 상세페이지