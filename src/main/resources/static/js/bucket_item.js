// 버킷리스트 쓰기
$(".active_btn").click(function() {
    const idx = $("#item_idx").val();
    const bucket_idx = $("#bucket_idx").val();
    const title = $("#bucket_title").val();
    const contents = getEditorContent();
    const achieve_date = $("#achieve_date").val();
    const address = $("#address").val();
    const address_detail = $("#address_detail").val();
    let achieve_yn = $('input[name=achieve_yn]:checked').val();
    if (achieve_yn == null || achieve_yn == undefined) {
        achieve_yn = 'N';
    }

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!contents){
        alert("내용을 입력해주세요");
        return;
    }

    let form_data = new FormData;
    form_data.append("idx", idx);
    form_data.append("bucketIdx", bucket_idx);
    form_data.append("title", title);
    form_data.append("contents", contents);
    form_data.append("date", achieve_date);
    form_data.append("address", address);
    form_data.append("addressDetail", address_detail);
    form_data.append("achieveYn", achieve_yn);

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/bucket/item/save',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const msg = data.msg ?? null;
            if(msg ?? null) {
                alert(msg);
            }

            if(data.code === "200") {
                location.replace("/bucket");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}

$(".disable_btn").click(function (){
    let idx = $("#bucket_idx").val();
    location.href= "/bucket/detail/" + idx;
});