$(".active_btn").click(function() {
    location.href = "/bucket/regist";
});

function clickBucket(element){
    let idx = $(element).data('idx');

    location.href = "/bucket/detail/" + idx;
}