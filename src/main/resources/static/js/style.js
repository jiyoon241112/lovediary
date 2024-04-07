// 로고 클릭
$(".logo").click(function() {
    location.href = "/";
});

// 팝업 닫기
$(".pop_close").click(function() {
    $(".popup, .pop").hide();
});

// 팝업 열기
function onPopup(id = "") {
    $(".popup").show();

    if(id) {
        id.replace("#", "");
        $(`#{id}`).show();
    }
}