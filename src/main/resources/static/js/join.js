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