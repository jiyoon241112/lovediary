//상세
function clickPlace(element){
    let idx = $(element).data('idx');

    location.href = "/place/detail/" + idx;
}

// 검색
$("#keyword").on("keydown", function(key) {
    if(key.keyCode === 13) {
        $("#search_btn").click();
    }
});

$("#search_btn").click(function() {
    const keyword = $("#keyword").val();
    location.replace(`/place/list?keyword=${keyword}`);
});

$("#change_btn").click(function(){
    if($(".list_frame_img").length > 0){
        $(".list_frame_img").removeClass("list_frame_img").addClass("list_list_img");
        $(".list").show();
        $(".item_list").hide();
    } else {
        $(".list_list_img").removeClass("list_list_img").addClass("list_frame_img");
        $(".list").hide();
        $(".item_list").show();
    }
});