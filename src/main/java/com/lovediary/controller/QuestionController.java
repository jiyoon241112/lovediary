package com.lovediary.controller;

import com.lovediary.service.AccountService;
import com.lovediary.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final AccountService accountService;
    public QuestionController(QuestionService questionService, AccountService accountService) {
        this.questionService = questionService;
        this.accountService = accountService;
    }

    @GetMapping("/question")
    public String questionPage(Model model) {
        model.addAttribute("list", questionService.getList());
        return "pages/question/question_list";
    }

    @GetMapping("/question/detail/{idx}")
    public String questionDetailPage(@PathVariable(name = "idx") Long idx, Model model) {
        model.addAttribute("answer", questionService.getOne(idx));
        model.addAttribute("comment_list", questionService.getCommentList(idx));
        return "pages/question/question_detail";
    }

    @GetMapping("/question/regist")
    public String questionRegistPage() {
        return "pages/question/question";
    }

    @GetMapping(value = {"/question/modify", "/question/modify/{idx}"})
    public String questionModifyPage(@PathVariable(name = "idx", required = false) Long idx, Model model) {
        if(idx == null) {
            model.addAttribute("answer", questionService.getToday());
        } else {
            model.addAttribute("answer", questionService.getOne(idx));
        }

        model.addAttribute("gender", accountService.getOne(2L).getGender());

        return "pages/question/question";
    }
}
