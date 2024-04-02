function amountFormat(){
    let amount = $("#amount").val();
    if(amount){
        amount = amount.replaceAll(",","");
        let result = parseInt(amount).toLocaleString('ko-KR');
        $("#amount").val(result);
    }
}

// 가계부 쓰기
$(".active_btn").click(function() {
    const due_date = $("#due_date").val();
    const amount = $("#amount").val().replace(/,/g, '');
    const contents = $("#content").val();
    // const categoryIdx = 

    let type = $('input[name=type]:checked').val();

    if(!due_date) {
        alert("발생일을 입력해주세요.");
        return;
    }

    if(!amount) {
        alert("금액을 입력해주세요.");
        return;
    }

    if(!contents){
        alert("내용을 입력해주세요");
        return;
    }

    if(!type){
        alert("출금/지출 여부를 클릭해주세요");
        return;
    }


    let form_data = new FormData;
    form_data.append("due_date", due_date);
    form_data.append("amount", amount);
    form_data.append("contents", contents);
    form_data.append("type", type);

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/household/save',
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
                location.replace("/");
            }
        }, error: function () {
            if(!retry) save(form_data, true);
        }
    });
}