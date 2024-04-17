$("#comment_btn").click(function (){
    let idx = $(this).data('idx');
    const contents = $("#comment_input").val();

    let form_data = new FormData;
    form_data.append("couple_diary_idx", idx);
    form_data.append("contents", contents);

    if(!contents) {
        alert("댓글을 작성해주세요.");
        return;
    }

    save(form_data);
});

$("#edit_btn").click(function(){
    let comment_txt = $(this).closest(".comment.card").find("p").text();
    let idx = $(this).closest(".comment.card").data("idx");

    $("#comment_idx").val(idx);
    $("#comment_edit_pop #comment_contents").val(comment_txt);
    onPopup("comment_edit_pop");
});

$("#save_answer").click(function (){
    let couple_diary_idx = $("#comment_btn").data("idx");
    let idx = $("#comment_idx").val();
    const contents = $("#comment_contents").val();

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("couple_diary_idx", couple_diary_idx);
    form_data.append("contents", contents);

    save(form_data);
});

$("#delete_btn").click(function(){
    let idx = $(this).closest(".comment.card").data("idx");
    let form_data = new FormData;
    form_data.append("idx", idx);

    deleteComment(form_data);
});

function deleteComment(form_data, retry = false) {
    $.ajax({
        url: '/diary/delete_comment',
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
                location.replace("/diary");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}

function save(form_data, retry = false) {
    $.ajax({
        url: '/diary/save_comment',
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
                location.replace("/diary");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}