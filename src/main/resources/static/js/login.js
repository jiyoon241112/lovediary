// 로그인
$("#login_btn").click(function() {
    const id = $("#id").val();
    const password = $("#password").val();

    if(!id) {
        alert("아이디를 입력해주세요.");
        return;
    }
    
    if(!password) {
        alert("비밀번호를 입력해주세요.");
        return;
    }

    let form_data = new FormData;
    form_data.append("id", id);
    form_data.append("password", password);

    login(form_data);
});

function login(form_data, retry = false) {
    $.ajax({
        url: '/login',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;
            if(msg ?? null) {
                alert(msg);
            }

            if(code === "200") {
                location.replace("/");
            }
        }, error: function () {
            if(!retry) login(form_data, true);
        }
    });
}

// 회원가입
$("#join_btn").click(function() {
    location.href = "/join";
});

// 비밀번호 찾기
$("#find_password_btn").click(function() {
    location.href = "/find_password";
});

// 카카오 로그인
$("#kakao_btn").click(function() {
    alert("카카오 로그인");
});

// 네이버 로그인
$("#naver_btn").click(function() {
    location.replace("/login/naver");
});