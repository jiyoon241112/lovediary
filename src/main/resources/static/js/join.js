let checked = false;

// 전화번호 수정
$("#change_phone_btn").click(function () {
    removeCode();
});

function removeCode(retry = false) {
    $.ajax({
        url: '/remove/code',
        method: 'post',
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                $("#code_input").hide();
                $("#phone").prop("disabled", false);
                $("#send_sms_btn").show();
                $("#change_phone_btn").hide();
                checked = false;
            }
        }, error: function () {
            if(!retry) removeCode(true);
        }
    });
}

// 인증 코드 발송
$("#send_sms_btn").click(function() {
    if(!$("#phone").val()) {
        alert("전화번호를 입력해주세요.");
        return;
    }

    sendCode();
});

function sendCode(retry = false) {
    $.ajax({
        url: '/send/sms',
        method: 'post',
        data : {phone: $("#phone").val()},
        async: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;

            if(msg) {
                alert(msg);
            }

            if(code === "200") {
                checked = false;
                $("#code_input").show();
                $("#phone").prop("disabled", true);
                $("#send_sms_btn").hide();
                $("#change_phone_btn").show();
            }
        }, error: function () {
            if(!retry) sendCode(true);
        }
    });
}

// 인증번호 확인
$("#check_code_btn").click(function() {
    if(!$("#code").val()) {
        alert("인증번호를 입력해주세요.");
        return;
    }

    checkCode();
});

function checkCode(retry = false) {
    $.ajax({
        url: '/check/code',
        method: 'post',
        data : {code: $("#code").val()},
        async: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;

            if(msg) {
                alert(msg);
            }

            if(code === "200") {
                $("#code_input").hide();
                checked = true;
            }
        }, error: function () {
            if(!retry) checkCode(true);
        }
    });
}

// 다음으로
$("#next_btn").click(function () {
    const name = $("#name").val();
    const love_name = $("#love_name").val();
    const phone = $("#phone").val();
    const id = $("#id").val();
    const password = $("#password").val();
    const password_check = $("#password_check").val();
    const gender = $("[name=gender]:checked").val();

    if(!gender) {
        alert("성별을 선택해주세요.");
        return;
    }

    if(!name) {
        alert("이름을 입력해주세요.");
        return;
    }

    if(!love_name) {
        alert("닉네임을 입력해주세요.");
        return;
    }

    if(!phone) {
        alert("휴대폰 번호를 입력해주세요.");
        return;
    }

    // 전화번호 확인이 되지 않았을 때
    if(!checked) {
        alert("전화번호를 인증해주세요.");
        return;
    }

    if(!id) {
        alert("아이디를 입력해주세요.");
        return;
    }

    if(!password) {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    if(password != password_check) {
        alert("비밀번호를 확인해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("gender", gender);
    form_data.append("name", name);
    form_data.append("loveName", love_name);
    form_data.append("phoneNumber", phone);
    form_data.append("id", id);
    form_data.append("password", password);

    next(form_data);
});

function next(form_data, retry = false) {
    $.ajax({
        url: '/join/1',
        method: 'post',
        data : form_data,
        async: false,
        contentType: false,
        processData: false,
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                location.replace("/join/2")
            } else {
                const msg = data.msg ?? null;
                if(msg) {
                    alert(msg);
                }
            }
        }, error: function () {
            if(!retry) next(true);
        }
    });
}