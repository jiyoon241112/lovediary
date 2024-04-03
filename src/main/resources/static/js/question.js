// 상세
$("#question_list").on("click", "li", function() {
    const answerIdx = $(this).data("idx")
    location.href = `/question/detail/${answerIdx}`;
});