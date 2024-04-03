$(".active_btn").click(function() {
    location.href = "/diary/regist";
});

function clickDiary(element){
    let idx = element.getAttribute('data-idx');

    location.href = "/diary/detail/" + idx;
}