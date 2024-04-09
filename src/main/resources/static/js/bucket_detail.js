$(".btn.active_btn.ml_30").click(function(){
    let idx = $(".bucket_idx").data('idx');

    location.href = "/bucket/modify/" + idx;
});