$(".calendar tbody").on("click", ".date", function() {
    const [year, month, date] = getCalendarDate();
    location.replace (`/schedule/detail?selected=${year}-${month}-${date}`);
});

$("#add_btn").click(function() {
    location.href = "/schedule/regist";
});

function editSchedule(element){
    let idx = $(element).data('idx');
    location.href = `/schedule/modify/${idx}`;
}

function getCalendarListAjax() {
    $.ajax({
        url: '/schedule/check',
        method: 'get',
        contentType: false,
        processData: false,
        success: function (data) {
            const code = data.code ?? null;

            if(code == 200) {
                const result = data.result ?? [];
                result.forEach(item => {
                    addEvent(item.year, item.month, item.day);
                });
            }
        }, error: function () {
            if(!retry) getPlaceListAjax(form_data, true);
        }
    });
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

$("#schedule_return").click(function(){
   location.replace("/schedule") ;
});

getCalendarListAjax();