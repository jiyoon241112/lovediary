const map_container = document.getElementById("map");
let map = null;
let ps = null;
let gc = null;
let marker_list = [];
let select_marker = null;

$(document).ready(function() {
    map = new kakao.maps.Map(map_container, {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
    });

    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다
        let latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        if(!select_marker) {
            const imageSize = new kakao.maps.Size(38, 48);
            const imageOption = {offset: new kakao.maps.Point(18, 48)};

            select_marker = new kakao.maps.Marker({
                position: latlng,
                map: map,
                image: new kakao.maps.MarkerImage("/resources/marker.svg", imageSize, imageOption)
            });
        } else {
            select_marker.setPosition(latlng);
        }
    });

    ps = new window.kakao.maps.services.Places();
    gc = new kakao.maps.services.Geocoder();

    // 주소 검색
    $("#keyword").on("keydown", function(key) {
        if(key.keyCode === 13) {
            $("#search_btn").click();
        }
    });

    $("#search_btn").click(function() {
        removeMarker();
        search($("#keyword").val());
    });
});

// 키워드 검색
function search(keyword) {
    ps.keywordSearch(keyword, function(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            let bounds = new kakao.maps.LatLngBounds();
            data.forEach(place => {
                setMarker(place.y, place.x);
                bounds.extend(new kakao.maps.LatLng(place.y, place.x));
            });

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
    });

    gc.addressSearch(keyword, function(data, status) {
        if (status === kakao.maps.services.Status.OK) {
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            let bounds = new kakao.maps.LatLngBounds();
            data.forEach(place => {
                setMarker(place.y, place.x);
                bounds.extend(new kakao.maps.LatLng(place.y, place.x));
            });

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
        }
    });
}

// 마커 생성
function setMarker(lat, lng) {
    // 마커를 생성합니다
    let marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(lat, lng),
        map: map
    });

    marker_list.push(marker);
}

// 마커 삭제
function removeMarker() {
    marker_list.forEach(marker => {
        marker.setMap(null);
        marker = null;
    });

    marker_list = [];
}