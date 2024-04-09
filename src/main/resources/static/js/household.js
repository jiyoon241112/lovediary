$(".active_btn").click(function() {
    location.href = "/household/regist";
});

function houseHoldSave(element){
    let idx = $(element).data('idx');

    location.href = "/household/modify/" + idx;
}

$(".money").click(function() {
    location.href = "/household/detail";
});

let params;
$('#check1').click(function() {
    params = [];

    if ($(this).is(':checked')) {
        params.push('type=O');
        $('#check2').prop('checked', false);
    }

    updateURL();
});

$('#check2').click(function() {
    params = [];

    if ($(this).is(':checked')) {
        params.push('type=I');
        $('#check1').prop('checked', false);
    }

    updateURL();
});

function updateURL() {
    let url = "/household";
    if (params.length > 0) {
        window.location.replace(url + '?' + params.join('&'));
    } else {
        window.location.replace(url);
    }
}

