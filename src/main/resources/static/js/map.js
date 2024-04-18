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
        markerSelect(latlng);
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

    kakao.maps.event.addListener(marker, 'click', function() {
        searchDetailAddrFromCoords(marker.getPosition(), function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                const detailAddr = !!result[0].road_address ? result[0].road_address.address_name: result[0].address.address_name;
                parent.getAddress(detailAddr, marker.getPosition().getLat(), marker.getPosition().getLng());
            }
        });
        markerSelect(marker.getPosition());
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

function chooseAddressInfo(){
    if(select_marker !== null && select_marker.getPosition() !== null){
        searchDetailAddrFromCoords(select_marker.getPosition(), function(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                const detailAddr = !!result[0].road_address ? result[0].road_address.address_name: result[0].address.address_name;
                parent.getAddress(detailAddr, select_marker.getPosition().getLat(), select_marker.getPosition().getLng());
            }
        });
        return 1;
    } else {
        return 0;
    }
}

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    gc.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    gc.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function markerSelect(latlng) {
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
}