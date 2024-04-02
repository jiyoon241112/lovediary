// 내용 입력시 글자수 체크
$("#contents_length").text($("#contents").val().length);
$("#contents").on("input", function() {
    $("#contents_length").text($("#contents").val().length);
});

// 기분 선택
$("#emotion").on("click", "div", function() {
    $(this).addClass("on").siblings().removeClass("on");
});

// 저장
$("#save_btn").click(function() {
    const idx = $("#idx").val();
    const contents = $("#contents").val();
    const emotion_idx = $("#emotion .on").data("idx");

    const only_whitespace = /^\s+$/;
    if(!contents || only_whitespace.test(contents)) {
        alert("내용을 입력해주세요.");
        return;
    }

    if(!emotion_idx) {
        alert("오늘의 기분을 선택해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("contents", contents);
    form_data.append("emotion_idx", emotion_idx);

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/question/save',
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
                if(result) {
                    location.replace(`/question/detail/${result}`);
                } else {
                    location.replace("/");
                }
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}