// 검색
$("#keyword").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#search_btn").click();
    }
});

$("#search_btn").click(function() {
    const keyword = $("#keyword").val();
    location.replace(`/course/list?keyword=${keyword}`);
});

function clickCategory(element){
    let idx = $(element).data('idx');

    location.href = "/course/list/" + idx;
}