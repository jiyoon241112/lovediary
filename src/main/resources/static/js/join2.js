// 프로필 이미지 업로드
$("#upload_file").on("change", function() {
    const files = $(this)[0].files;
    if(!files.length) {
        alert("파일을 업로드해주세요.");
        $(this).val("");
        return;
    }

    const upload_file = files[0];

    let form_data = new FormData;
    form_data.append("file", upload_file);
    form_data.append("type", 1);

    fileUpload(form_data);
    getBase64(upload_file, function(e) {
        $("#profile_image").css("background-image", `url('${e.srcElement.result}')`).removeClass("no_image");
    });
    $(this).val("");
});

function fileUpload(form_data, retry = false) {
    $.ajax({
        url: '/upload',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            if(data.code === "200") {
                $("#profile_idx").val(data.result);
            }
        }, error: function () {
            if(!retry) fileUpload(form_data, true);
        }
    });
}

// 회원가입 버튼
$("#join_btn").click(function() {
    const profile_idx = $("#profile_idx").val();
    const birth_day = $("#birth_day").val();
    const mbti = $("#mbti option:selected").val();
    const blood_type = $("[name=blood_type]:checked").val();

    let form_data = new FormData;
    form_data.append("profileIdx", profile_idx);
    form_data.append("birth_day", birth_day);
    form_data.append("mbti", mbti);
    form_data.append("bloodType", blood_type);

    join(form_data);
});

function join(form_data, retry = false) {
    $.ajax({
        url: '/join/2',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            if(data.code === "200") {
                location.replace("/join/3");
            }
        }, error: function () {
            if(!retry) join(form_data, true);
        }
    });
}