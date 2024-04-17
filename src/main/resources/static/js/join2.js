// 선택
$("[name=type]").on("change",function(){
    const value = $(this).val();

    $("#name, #code").val("");
    $(".join_tab").hide();
    $(`#tab${value}`).show();
});

// 다음
$("#next_btn").click(function(){
    if($("[name=type]:checked").val() == "1") {
        if(!$("#name").val()) {
            alert("커플명을 입력해주세요.");
            return;
        }

        makeCouple();
    } else {
        if(!$("#code").val()) {
            alert("코드를 입력해주세요.");
            return;
        }

        checkCode();
    }
});

function makeCouple(retry = false) {
    $.ajax({
        url: '/couple/regist',
        method: 'post',
        data : {name: $("#name").val()},
        async: false,
        success: function (data) {
            const msg = data.msg ?? null;
            const code = data.code ?? null;

            if(msg) {
                alert(msg);
            }

            if(code === "200") {
                location.replace("/join/3");
            }
        }, error: function () {
            if(!retry) checkCode(true);
        }
    });
}

function checkCode(retry = false) {
    $.ajax({
        url: '/couple/code',
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
                location.replace("/join/4");
            }
        }, error: function () {
            if(!retry) checkCode(true);
        }
    });
}