package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuestionController {
    @GetMapping("/question")
    public String questionPage() {
        return "pages/question/question_list";
    }

    @GetMapping("/question/detail")
    public String questionDetailPage() {
        return "pages/question/question_detail";
    }

    @GetMapping("/question/regist")
    public String questionRegistPage() {
        return "pages/question/question";
    }

    @GetMapping("/question/modify")
    public String questionModifyPage() {
        return "pages/question/question";
    }
}
