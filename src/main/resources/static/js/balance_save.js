let selected_index = null;

// 뒤로가기
$("#back_btn").click(function() {
    const idx = $("#idx").val();

    if(idx) {
        location.replace(`/balance/detail/${idx}`);
    } else {
        location.replace("/balance");
    }
});

// 답변 추가
$("#add_answer").click(function() {
    selected_index = null;

    $("#answer_idx").val("");
    $("#answer_contents").val("");
    onPopup("add_answer_pop");
});

// 답변 수정
$("#answer_list").on("click", "p", function() {
    const idx = $(this).data("idx");
    const answer_contents = $(this).text();

    selected_index = $(this).index();

    $("#answer_idx").val(idx);
    $("#answer_contents").val(answer_contents);
    onPopup("add_answer_pop");
});

$("#save_answer").click(function() {
    const answer_idx = $("#answer_idx").val();
    const answer_contents = $("#answer_contents").val();

    if(!answer_contents) {
        alert("답변 내용을 입력해주세요.");
        return;
    }

    if(selected_index != null) {
        $("#answer_list p").eq(selected_index).text(answer_contents);
    } else {
        $("#answer_list").append(`<p class="money bold_t left_t" data-idx="${answer_idx}">${answer_contents}</p>`);
    }

    $(".background.pop_close").click();
});

// 저장
$("#save_btn").click(function() {
    const idx = $("#idx").val();
    const title = $("#title").val();
    const contents = getEditorContent();
    const _item_list = $("#answer_list p");

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!contents) {
        alert("내용을 입력해주세요.");
        return;
    }

    if(!_item_list.length) {
        alert("답변을 추가해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("title", title);
    form_data.append("contents", contents);

    let item_list = [];
    _item_list.each((idx, item) => {
        item = $(item);
        item_list.push({idx: item.data("idx"), title: item.text()});
    });
    form_data.append("item_list", JSON.stringify(item_list));

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/balance/save',
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
                    location.replace("/balance");
                }

                location.replace(`/balance/detail/${result}`);
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}