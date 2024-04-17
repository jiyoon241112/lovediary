// 버킷리스트 쓰기
$(".active_btn").click(function() {
    const title = $("#bucket_title").val();
    const contents = getEditorContent();
    let idx = $(".form_group").data('idx');

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!contents){
        alert("내용을 입력해주세요");
        return;
    }

    let form_data = new FormData;
    form_data.append("title", title);
    form_data.append("contents", contents);
    if(idx !== undefined && idx !== null) {
        form_data.append("idx", idx);
    }

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/bucket/save',
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
                let idx = $("#bucket_idx").data('idx');

                if(idx !== undefined && idx !== null) {
                    location.replace("/bucket/detail/"+idx);
                } else {
                    location.replace("/bucket");
                }
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}