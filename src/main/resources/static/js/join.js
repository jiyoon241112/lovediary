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

// 다음 버튼
$("#next_btn").click(function() {
    const upload_file       = $("#upload_file").val();
    const name              = $("#name").val();
    const love_name         = $("#love_name").val();
    const id                = $("#id").val();
    const password          = $("#password").val();
    const password_check    = $("#password_check").val();

    if(!name) {
        alert("이름을 입력해주세요.");
        return;
    }

    if(!id) {
        alert("아이디를 입력해주세요.");
        return;
    }

    if(!password) {
        alert("패스워드를 입력해주세요.");
        return;
    }

    if(password !== password_check) {
        alert("패스워드를 확인해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("upload_file", upload_file);
    form_data.append("name", name);
    form_data.append("love_name", love_name);
    form_data.append("id", id);
    form_data.append("password", password);

    join(form_data);
});

function join(form_data, retry = false) {
    $.ajax({
        url: '/join/1',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            if(data.code === "200") {
                location.replace("/join/2");
            } else {
                const msg = data.msg ?? null;
                if(msg ?? null) {
                    alert(msg);
                }
            }
        }, error: function () {
            if(!retry) join(form_data, true);
        }
    });
}