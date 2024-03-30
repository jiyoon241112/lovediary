// 타입캡슐 쓰기
$(".active_btn").click(function() {
    const title = $("#capsule_title").val();
    const open_date = $("#open_date").val();

    let form_data = new FormData;
    form_data.append("title", title);
    form_data.append("open_date", open_date);

    if(!title) {
        alert("제목을 입력해주세요.");
        return;
    }

    if(!open_date) {
        alert("열릴 날짜를 입력해주세요.");
        return;
    }

    save(form_data);
});

function save(form_data, retry = false) {
    $.ajax({
        url: '/timecapsule/save',
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

const editor = new toastui.Editor({
    el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
    height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
    initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    initialValue: '내용을 입력해 주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
    previewStyle: 'vertical'                // 마크다운 프리뷰 스타일 (tab || vertical)
});