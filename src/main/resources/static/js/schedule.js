$(".calendar tbody").on("click", ".date", function() {
    const [year, month, date] = getCalendarDate();
    location.replace (`/schedule/detail?selected=${year}-${month}-${date}`);
});

$("#add_btn").click(function() {
    onPopup("schedule_popup");
});

$("#schedule_save").click(function (){
    const title = $("#schedule_title").val();
    const start_date = $("#start_date").val();
    const end_date = $("#end_date").val();
    const address = $("#address").val();
    const address_detail = $("#address_detail").val();
    const idx = $("#add_idx").val();

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

function editSchedule(element){
    let idx = $(element).data('idx');
    edit(idx);
}

function edit(idx, retry = false) {
    $.ajax({
        url: `/schedule/detail/${idx}`,
        method: 'get',
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data.result);
            if(data.code == 200) {
                const result = data.result;
                $("#add_idx").val(result.idx);
                $("#schedule_title").val(result.title);
                $("#end_date").val(result.endDateStr);
                $("#start_date").val(result.startDateStr);
                $("#address").val(result.address);
                $("#address_detail").val(result.addressDetail);
            }
            onPopup("schedule_popup");
        }, error: function () {
            if(!retry) edit(idx, true);
        }
    });
}