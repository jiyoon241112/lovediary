// 코드 복사
$("#copy_code").click(function() {
    navigator.clipboard.writeText($(this).text())
});

// 코드 입력 확인
let interval = setInterval(checkJoin, 3000);

function checkJoin(retry = false) {
    $.ajax({
        url: '/couple/check',
        method: 'post',
        async: false,
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                alert("코드가 입력되었습니다.");
                clearInterval(interval);
                $("#next_btn").removeClass("disabled_btn").addClass("active_btn");
                $("#next_btn.active_btn").click(function () {
                    location.replace("/login");
                });
            }
        }, error: function () {
            if(!retry) checkCode(true);
        }
    });
}