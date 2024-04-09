// 로고 클릭
$(".logo").click(function() {
    location.href = "/";
});

// 뒤로가기
$("#back_btn").click(function() {
    history.back();
});

// 팝업 이벤트 버블링 방지
$(".popup").on("scroll click", function(e) {
    e.stopPropagation();
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
        $(`#${id}`).show();
    }
}