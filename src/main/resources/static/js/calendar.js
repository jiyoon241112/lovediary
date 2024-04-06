const today_year = Number($("#year").val());
const today_month = Number($("#month").val());
const today_date = Number($("#day").val());

let year = today_year;
let month = today_month;

let date = new Date(year, month - 1, day);
let event_list = {};

// 전월
$("#calendar_prev_btn").click(function() {
    prevMonth();
});

// 익월
$("#calendar_next_btn").click(function() {
    nextMonth();
});

// 날짜 선택
$(".calendar tbody").on("click", ".date", function() {
    $(".calendar tbody .date.on").removeClass("on");
    $(this).addClass("on");
});

function prevMonth() {
    month--;
    if(month < 1) {
        year--;
        month = 12;
    }

    date = new Date(year, month - 1, 1);

    drawCalendar();
}

function nextMonth() {
    month++;
    if(month > 12) {
        year++;
        month = 1;
    }

    date = new Date(year, month - 1, 1);

    drawCalendar();
}

function drawCalendar() {
    const dom = $("#full_calendar tbody");
    let row = 1;

    let first_date = new Date(year, month - 1, 1);
    let last_date = new Date(year, month, 0);

    $("#calendar_year").text(year);
    $("#calendar_month").text((new Array(2 - String(month).length + 1).join("0")) + month);

    dom.empty();
    dom.append(`<tr data-row="${row++}"></tr>`);

    let before_days = (Math.ceil((first_date.getDay() + last_date.getDate()) / 7) * 7) - first_date.getDay();
    let week_day = 1;

    // 달력 그리기
    for(let day = 1 - first_date.getDay(); before_days >= day; day++) {
        let tr = dom.find("tr:last-of-type");

        let color = "";
        let make_new_row = false;
        let event_date = false;

        let this_year = null;
        let this_month = null;
        let this_day = null;

        // 평일( 전월일과 익월일의 데이터 제외 )
        if (Math.sign(day) === 1 && last_date.getDate() >= day) {
            // 일요일인 경우
            if (week_day % 7 === 1) {
                color = "#FF4D4D";
            }

            // 토요일인 경우
            if (week_day % 7 === 0) {
                color = "#4D4DFF";
                make_new_row = true;
            }

            this_year = first_date.getFullYear();
            this_month = first_date.getMonth();
            this_day = day;
        } else {
            let exceptDay = new Date(first_date.getFullYear(), first_date.getMonth(), day);
            color = "#A9A9A9";

            this_year = first_date.getFullYear();
            if(row < 3) {
                this_month = first_date.getMonth() - 1;

                if(this_month < 0) {
                    this_year = first_date.getFullYear() - 1;
                    this_month = 11;
                }
            } else {
                this_month = first_date.getMonth() + 1;

                if(this_month > 11) {
                    this_year = first_date.getFullYear() + 1;
                    this_month = 0;
                }
            }

            this_day = exceptDay.getDate();
        }

        tr.append(`<td class="date" data-year="${this_year}" data-month="${this_month + 1}" data-day="${this_day}">${(new Array(2 - String(this_day).length + 1).join("0")) + this_day}</td>`);
        let column = tr.find("td:last-of-type");

        if(color) {
            column.css("color", color);
        }

        if(this_year === today_year && (this_month + 1) === today_month && this_day === today_date) {
            column.addClass("on");
        }

        if(event_list[this_year]) {
             if(event_list[this_year][this_month + 1]) {
                 if(event_list[this_year][this_month + 1][this_day]) {
                     column.addClass("event");
                 }
             }
        }

        if(make_new_row) {
            dom.append(`<tr data-row="${row++}"></tr>`);
        }

        week_day++;
    }
}

function addEvent(year, month, day) {
    if(!event_list[year]) {
        event_list[year] = {};
    }

    if(!event_list[year][month]) {
        event_list[year][month] = {};
    }

    event_list[year][month][day] = "on";
    $(`.calendar tbody .date[data-year=${year}][data-month=${month}][data-day=${day}]`).addClass("event");
}

function getCalendarDate() {
    const selected_date = $(".calendar tbody .date.on");
    if(!selected_date.length) {
        return [null, null, null];
    }
    return [selected_date.data("year"), selected_date.data("month"), selected_date.data("day")];
}

drawCalendar();