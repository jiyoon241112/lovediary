// 뒤로가기
$("#back_btn").click(function() {
    const idx = $("#idx").val();

    if(idx) {
        location.replace(`/community/detail/${idx}`);
    } else {
        location.replace("/community");
    }
});

// 저장
$("#save_btn").click(function() {
    const idx = $("#idx").val();
    const title = $("#title").val();
    const contents = getEditorContent();

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!contents) {
        alert("내용을 입력해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("title", title);
    form_data.append("contents", contents);

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/community/save',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;
            if(msg ?? null) {
                alert(msg);
            }

            if(code === "200") {
                const result = data.result ?? null;
                if(!result) {
                    location.replace("/community");
                }

                location.replace(`/community/detail/${result}`);
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}