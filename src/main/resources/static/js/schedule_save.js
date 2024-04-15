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

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!start_date){
        alert("시작일자를 입력해주세요");
        return;
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