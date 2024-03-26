package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {
    @GetMapping("/diary")
    public String diaryPage() {
        return "pages/diary/diary_list";
    }

    @GetMapping("/diary/detail")
    public String diaryDetailPage() {
        return "pages/diary/diary_detail";
    }

    @GetMapping("/diary/regist")
    public String diaryRegistPage() {
        return "pages/diary/diary";
    }

    @GetMapping("/diary/modify")
    public String diaryModifyPage() {
        return "pages/diary/diary";
    }
}
