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