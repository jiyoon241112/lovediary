let address = null;
let get_lat = null;
let get_lng = null;

// 주소 검색
$("#address").on("focus", function() {
    onPopup("map_popup");
});

// 저장
$("#schedule_save").click(function (){
    const title = $("#schedule_title").val();
    const start_date = $("#start_date").val();
    const end_date = $("#end_date").val();
    const address = $("#address").val();
    const address_detail = $("#address_detail").val();
    const idx = $("#idx").val();
    const dateTimePattern = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/;

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!start_date){
        alert("시작일자를 입력해주세요");
        return;
    } 
    
    if (!dateTimePattern.test(start_date)) {
        alert("날짜 형식을 yyyy-mm-dd hh:mm로 적어주세요.");
    }

    let form_data = new FormData;
    form_data.append("title", title);
    form_data.append("start_date", start_date);
    form_data.append("end_date", end_date);
    form_data.append("address", address);
    form_data.append("addressDetail", address_detail);
    if(idx !== undefined && idx !== null) {
        form_data.append("idx", idx);
    }
    
    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/schedule/save',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const msg = data.msg ?? null;
            if(msg ?? null) {
                alert(msg);
            }

            if(data.code === "200") {
                location.replace("/schedule");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}

$("#addr_select").click(function (){
    let address_info = $('#address_info')[0].contentWindow.chooseAddressInfo();
    if(address_info == 0){
        alert("장소를 선택해주세요.");
    } else if(address_info == 1) {
        $(".popup, .pop").hide();
    }


});

function getAddress(detail_address, lat, lng){
    address = detail_address;
    get_lat = lat;
    get_lng = lng;
    $("#address").val(address);
}