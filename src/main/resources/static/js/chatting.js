const socket = io('ws://localhost:8080');
const couple_idx = $("#couple_idx").val();
const account_idx = $("#account_idx").val();
const idx_list = [];
const date_list = [];
let get_date = null;
let last_date = null;
let upload_file = null;

socket.on('join', data => {
    console.log("JOINED CHATTING SERVER");
});

socket.on('chatting', data => {
    const today = new Date();
    const month = (today.getMonth() + 1) < 9 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1);
    const day = (today.getDate() + 1) < 9 ? "0" + (today.getDate() + 1) : (today.getDate() + 1);
    const date = `${today.getFullYear()}-${month}-${day}`;

    if(last_date == null) {
        last_date = date;
    }

    if(last_date > date) {
        $("#chatting").append(showDate(date));
    }

    $("#chatting").append(showChat(data.idx, (data.account_idx == account_idx ? "right_t" : "left_t"), data.image_idx ?? null, data.contents ?? null));
});

socket.emit('join', couple_idx);

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
        url: '/chatting/upload',
        method: 'post',
        data : form_data,
        contentType: false,
        processData: false,
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                const result = data.result ?? null;
                socket.emit('chatting', {idx: result.idx, account_idx: account_idx, image_idx: result.image_idx, contents: null});
            } else {
                const msg = data.msg ?? null;

                if(msg ?? null) {
                    alert(msg);
                }
            }
        }, error: function () {
            if(!retry) fileUpload(form_data, true);
        }
    });
}

// 이미지 미리보기
$(document).on("click", ".image_preview .chat", function() {
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

// 채팅 입력
$("#comment").on("keydown", function(e) {
    if(e.keyCode === 13) {
        $("#save_comment").click();
    }
});

$("#save_comment").click(function() {
    const contents = $("#comment").val();

    if(!contents) {
        alert("채팅 내용을 입력해주세요.");
        return;
    }

    saveComment(contents);
    $("#comment").val("");
});

function saveComment(contents, retry = false) {
    $.ajax({
        url: '/chatting/save',
        method: 'post',
        data : {contents: contents},
        success: function (data) {
            const code = data.code ?? null;

            if(code === "200") {
                socket.emit('chatting', {idx: data.result ?? null, couple_idx: couple_idx, account_idx: account_idx, image_idx: null, contents: contents});
                $('#chatting').scrollTop(chatting.scrollHeight);
            } else {
                const msg = data.msg ?? null;

                if(msg ?? null) {
                    alert(msg);
                }
            }
        }, error: function () {
            if(!retry) saveComment(contents, true);
        }
    });
}

// 스크롤 맨 위로 올리면 다음 날짜 가져오기
$("#chatting").scroll(function(){
    if(0 >= $("#chatting").scrollTop() - $("#chatting").height()) {
        getDate();
    }
});

// 채팅 기록 조회
function getChatting(retry = false) {
    $.ajax({
        url: '/chatting/get',
        method: 'get',
        data : {date: get_date},
        async: false,
        success: function (data) {
            result = data.result ?? null;

            if(result) {
                result.forEach(item => {
                    const image_idx = item.imageIdx ?? null;
                    const date = item.registDateStr;

                    if(last_date == null) {
                        last_date = date;
                    }

                    if(last_date > date) {
                        $("#chatting").prepend(showDate(date));
                    }

                    $("#chatting").prepend(showChat(item.idx, (item.accountIdx == account_idx ? "right_t" : "left_t"), image_idx, item.contents ?? null));
                });

                $("#chatting").prepend(showDate(last_date));
                $('#chatting').scrollTop(chatting.scrollHeight);
            }
        }, error: function () {
            if(!retry) getChatting(true);
        }
    });
}

// 채팅 내용 보여주기
function showChat(idx, class_name, image_idx = null, contents = null) {
    if(!idx_list.includes(idx)) {
        idx_list.push(idx);

        if(image_idx) {
            return `<div class="image_preview ${class_name} w_full">
                        <div class="r_30 bold_t ml_30 mr_30 chat" data-image="${image_idx}">
                            <img src="/view/image/${image_idx}" class="chat_img"/>
                        </div>
                    </div>`;
        } else {
            return `<div class="${class_name} bold_t w_full">
                        <div class="r_30 bold_t ml_30 mr_30 chat">${contents}</div>
                    </div>`;
        }
    }

    return "";
}

function showDate(date) {
    const show_date = last_date;

    if(last_date > date) {
        last_date = date;
        return "";
    } else {
        return `<div class="center_t w_full">
                    <div class="r_30 bold_t lh_40">${show_date}</div>
                </div>`;
    }
}

// 최근 채팅 날짜 조회
function getDate(retry = false) {
    if(date_list.includes(get_date) && !retry) {
        return;
    }
    date_list.push(get_date);

    $.ajax({
        url: '/get/date',
        method: 'get',
        data : {date: get_date},
        async: false,
        success: function (data) {
            result = data.result ?? null;
            get_date = result;

            if(result) {
                getChatting();
            }
        }, error: function () {
            if(!retry) getDate(true);
        }
    });
}

getDate();