$(".btn.active_btn.ml_30").click(function(){
    let idx = $(".bucket_idx").data('idx');

    location.href = "/bucket/modify/" + idx;
});

$(".w_270.active_btn.bold_t").click(function(){
    let idx = $(".bucket_idx").data('idx');
    location.href = "/bucket/item/" + idx;
});

function clickItem(element){
    let idx = $(element).data('idx');

    location.href = "/bucket/item/" + idx;
}

$(".add_btn").click(function (){
    let idx = $(".bucket_idx").data('idx');
    location.href = "/bucket/item?bucket_idx=" + idx;
});

$("#return_bucket").click(function(){
   location.href = "/bucket";
});

$("#delete_btn").click(function (){
    let idx = $(this).closest(".comment.card").data("idx");
    let form_data = new FormData;
    form_data.append("idx", idx);

    deleteBucket(form_data);
});

function deleteBucket(form_data, retry = false) {
    $.ajax({
        url: '/bucket/delete',
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
                location.replace("/bucket");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}

