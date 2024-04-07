const map_container = document.getElementById("map");
let map = null;

$(document).ready(function() {
    map = new kakao.maps.Map(map_container, {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
    });
});