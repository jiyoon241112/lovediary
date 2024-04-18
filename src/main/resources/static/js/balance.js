// 작성 페이지
$("#write_btn").click(function() {
    location.href = "/balance/regist";
});

// 수정 페이지
$("#modify_btn").click(function() {
    const idx = $("#idx").val();
    location.href = `/balance/modify/${idx}`;
});

// 검색
$("#keyword").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#search_btn").click();
    }
});

$("#search_btn").click(function() {
    const keyword = $("#keyword").val();
    location.replace(`/balance?keyword=${keyword}`);
});

// 상세
$("#balance_list").on("click", "li", function() {
    const idx = $(this).data("idx");
    location.href = `/balance/detail/${idx}`;
});

// 목록
$("#list_btn").click(function() {
    location.href = "/balance";
});

// 답변 선택
$("#answer_list").on("click", "p", function() {
    const idx = $("#idx").val();
    const item_idx = $(this).data("idx");

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("item_idx", item_idx);

    saveAnswer(form_data);
});

function saveAnswer(form_data, retry = false) {
    $.ajax({
        url: '/balance/save_answer',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;

            if(code === "200") {
                location.reload();
            }
        }, error: function () {
            if(!retry) saveAnswer(form_data, true);
        }
    });
}

// 댓글 입력
$("#comment").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#save_comment").click();
    }
});

$("#save_comment").click(function() {
    const balance_idx = $("#idx").val();
    const reply_idx = $("#reply_idx").length ? $("#reply_idx").val() : 0;
    const comment = $("#comment").val();

    if(!comment) {
        alert("댓글 내용을 입력해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("balance_idx", balance_idx);
    form_data.append("reply_idx", reply_idx);
    form_data.append("contents", comment);

    saveComment(form_data);
});

$("#modify_comment").click(function (){
    const idx = $("#comment_idx").val();
    const contents = $("#comment_contents").val();

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("contents", contents);

    saveComment(form_data);
});

function saveComment(form_data, retry = false) {
    $.ajax({
        url: '/balance/save_comment',
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
                location.reload();
            }
        }, error: function () {
            if(!retry) saveComment(form_data, true);
        }
    });
}

// 댓글 상세
$("#comment_list").on("click", ".comment", function() {
    const idx = $(this).data("idx");
    location.href = `/balance/comment/${idx}`;
});

// 댓글 수정
$("#edit_btn, #comment_edit_btn").click(function(e){
    e.stopPropagation();

    const comment_txt = $(this).closest(".comment.card").find("p").text();
    const idx = $(this).closest(".comment.card").data("idx");

    $("#comment_idx").val(idx);
    $("#comment_edit_pop #comment_contents").val(comment_txt);
    onPopup("comment_edit_pop");
});

// 댓글 삭제
$("#delete_btn, #comment_delete_btn").click(function(e){
    e.stopPropagation();

    if(!confirm("삭제하시겠습니까?")) {
        return;
    }

    const idx = $(this).closest(".comment.card").data("idx");

    let form_data = new FormData;
    form_data.append("idx", idx);

    deleteComment(form_data);
});

function deleteComment(form_data, retry = false) {
    $.ajax({
        url: '/balance/remove_comment',
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
                if($("#reply_idx").length) {
                    location.replace(`/balance/detail/${$("#idx").val()}`)
                } else {
                    location.reload();
                }
            }
        }, error: function () {
            if(!retry) deleteComment(form_data, true);
        }
    });
}

// 게시글 삭제
$("#remove_item_btn").click(function() {
    if(!confirm("삭제하시겠습니까?")) {
        return;
    }

    deleteItem();
});

function deleteItem(retry = false) {
    $.ajax({
        url: '/balance/remove',
        method: 'post',
        data : {idx: $("#idx").val()},
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;

            if(msg ?? null) {
                alert(msg);
            }

            if(code === "200") {
                location.replace(`/balance`)
            }
        }, error: function () {
            if(!retry) deleteItem(true);
        }
    });
}