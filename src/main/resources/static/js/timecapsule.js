$(".active_btn").click(function() {
    location.href = "/timecapsule/regist";
});

function clickTimeCapsule(element){
    let idx = element.getAttribute('data-idx');

    location.href = "/timecapsule/detail/" + idx;
}