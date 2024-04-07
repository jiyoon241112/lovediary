// 작성 페이지
$("#write_btn").click(function() {
    location.href = "/community/regist";
});

// 수정 페이지
$("#modify_btn").click(function() {
    const idx = $("#idx").val();
    location.href = `/community/modify/${idx}`;
});

// 검색
$("#keyword").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#search_btn").click();
    }
});

$("#search_btn").click(function() {
    const keyword = $("#keyword").val();
    location.replace(`/community?keyword=${keyword}`);
});

// 상세
$("#community_list").on("click", "li", function() {
    const idx = $(this).data("idx");
    location.href = `/community/detail/${idx}`;
});

// 목록
$("#list_btn").click(function() {
    location.href = "/community";
});

// 댓글 입력
$("#comment").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#save_comment").click();
    }
});

$("#save_comment").click(function() {
    const idx = $("#idx").val();
    const reply_idx = $("#reply_idx").length ? $("#reply_idx").val() : 0;
    const comment = $("#comment").val();

    if(!comment) {
        alert("댓글 내용을 입력해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("reply_idx", reply_idx);
    form_data.append("contents", comment);

    saveComment(form_data);
});

function saveComment(form_data, retry = false) {
    $.ajax({
        url: '/community/save_comment',
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
    location.href = `/community/comment/${idx}`;
});