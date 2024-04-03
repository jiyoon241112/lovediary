// 일기 쓰기
$(".active_btn").click(function() {
    const title = $("#title").val();
    const diary_content = $("#diary_contents").val();

    let form_data = new FormData;
    form_data.append("title", title);
    form_data.append("contents", diary_content);

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!diary_content) {
        alert("내용을 입력해주세요.");
        return;
    }

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/diary/save',
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
                location.replace("/");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}