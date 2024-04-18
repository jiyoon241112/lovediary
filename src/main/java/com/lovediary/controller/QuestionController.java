package com.lovediary.controller;

import com.lovediary.service.AccountService;
import com.lovediary.service.QuestionService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * QuestionController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
@Controller
public class QuestionController extends Session {
    private final QuestionService questionService;
    private final AccountService accountService;
    public QuestionController(QuestionService questionService, AccountService accountService) {
        this.questionService = questionService;
        this.accountService = accountService;
    }

    // 오늘의 질문 목록 페이지
    @GetMapping("/question")
    public String questionPage(Model model) {
        model.addAttribute("list", questionService.getList());

        return "pages/question/question_list";
    }

    // 오늘의 질문 상세 페이지
    @GetMapping("/question/detail/{idx}")
    public String questionDetailPage(HttpServletRequest request,
                                     @PathVariable(name = "idx") Long idx,
                                     Model model) {
        model.addAttribute("answer", questionService.getOne(idx));
        model.addAttribute("comment_list", questionService.getCommentList(idx));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/question/question_detail";
    }

    // 오늘의 질문 수정 페이지
    @GetMapping(value = {"/question/modify", "/question/modify/{idx}"})
    public String questionModifyPage(HttpServletRequest request,
                                     @PathVariable(name = "idx", required = false) Long idx,
                                     Model model) {
        if(idx == null) {
            model.addAttribute("answer", questionService.getToday());
        } else {
            model.addAttribute("answer", questionService.getOne(idx));
        }

        model.addAttribute("gender", accountService.getOne(this.getLoginData(request).getAccountIdx()).getGender());

        return "pages/question/question";
    }
}
