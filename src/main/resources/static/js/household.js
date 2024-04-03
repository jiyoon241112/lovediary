$(".active_btn").click(function() {
    location.href = "/household/regist";
});

function clickHouseHold(element){
    let idx = element.getAttribute('data-idx');

    location.href = "/household/detail/" + idx;
}