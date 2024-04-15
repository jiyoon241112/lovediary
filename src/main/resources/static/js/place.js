let latitude = null;
let longitude = null;

$(document).ready(function() {
    getCurrentLocation();
});

//상세
function clickPlace(element){
    let idx = $(element).data('idx');

    location.href = "/place/detail/" + idx;
}

// 검색
$("#keyword").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#search_btn").click();
    }
});

$("#search_btn").click(function() {
    const keyword = $("#keyword").val();
    location.replace(`/place/list?keyword=${keyword}`);
});

$("#map_btn").click(function() {
    location.href = "/place/map";
});

$("#change_btn").click(function(){
    if($(".list_frame_img").length > 0){
        $(".list_frame_img").removeClass("list_frame_img").addClass("list_list_img");
        $(".list").show();
        $(".item_list").hide();
    } else {
        $(".list_list_img").removeClass("list_list_img").addClass("list_frame_img");
        $(".list").hide();
        $(".item_list").show();
    }
});

// 현재 위치
function getCurrentLocation() {
    navigator.geolocation.getCurrentPosition(function(pos) {
        const crd = pos.coords;
        latitude = crd.latitude;
        longitude = crd.longitude;

        getPlaceList();
    }, function(err) {
        console.warn(`ERROR(${err.code}): ${err.message}`);

        getPlaceList();
    }, {
        enableHighAccuracy: true,
        timeout: 5000,
        maximumAge: 0,
    });
}

function getPlaceList() {
    const theme_idx = $("#theme_idx").val();
    const keyword = $("#keyword").val();

    let form_data = new FormData;
    form_data.append("theme_idx", theme_idx);
    form_data.append("keyword", keyword);
    form_data.append("latitude", latitude);
    form_data.append("longitude", longitude);

    getPlaceListAjax(form_data)
}

function getPlaceListAjax(form_data, retry = false) {
    $.ajax({
        url: '/place/get_place',
        method: 'get',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const code = data.code ?? null;

            if(code == 200) {
                const result = data.result ?? [];
                result.forEach(item => {
                    console.log(item);
                });
            }
        }, error: function () {
            if(!retry) getPlaceListAjax(form_data, true);
        }
    });
}