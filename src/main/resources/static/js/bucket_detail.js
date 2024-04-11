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

