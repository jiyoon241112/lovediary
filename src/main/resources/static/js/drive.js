let upload_file = null;

// 이미지 저장
$("#image_btn").click(function() {
    $("#upload_file").val("");
    $("#upload_image").css("background-image", "").addClass("no_image");

    onPopup("image_upload_pop");
});

$("#upload_file").on("change", function() {
    const files = $(this)[0].files;
    if(!files.length) {
        alert("파일을 업로드해주세요.");
        $(this).val("");
        return;
    }

    upload_file = files[0];

    getBase64(upload_file, function(e) {
        $("#upload_image").css("background-image", `url('${e.srcElement.result}')`).removeClass("no_image");
    });
});

$("#save_image_btn").click(function() {
    if(upload_file == null) {
        alert("파일을 업로드해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("file", upload_file);

    saveFile(form_data);
});

function saveFile(form_data, retry = false) {
    $.ajax({
        url: '/drive/upload',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const code = data.code ?? null;
            const msg = data.msg ?? null;

            if(msg ?? null) {
                alert(msg);
            }

            if(code === "200") {
                location.reload();
            }
        }, error: function () {
            if(!retry) fileUpload(form_data, true);
        }
    });
}

// 이미지 미리보기
$("#image_list li").click(function() {
    const image_idx = $(this).data("image");

    $("#image").attr("src", `/view/image/${image_idx}`);
    $("#image_view_pop").data("image", image_idx);

    onPopup("image_view_pop");
});

// 이미지 다운로드
$("#download_image_btn").click(function() {
    const image_idx = $("#image_view_pop").data("image");

    location.href = `/download/${image_idx}`;
});