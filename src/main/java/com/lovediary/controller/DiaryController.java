package com.lovediary.controller;

import com.lovediary.dto.AlarmDto;
import com.lovediary.dto.DiaryDto;
import com.lovediary.service.AlarmService;
import com.lovediary.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiaryController {
    private DiaryService diaryService;
    public DiaryController(DiaryService service) {
        this.diaryService = service;
    }

    // <커플 다이어리 리스트 페이지>
    @GetMapping("/diary")
    public String diaryPage(Model model) {
        model.addAttribute("list", diaryService.getList());
        return "pages/diary/diary_list";
    }

    // <커플 다이어리 상세 페이지>
    @GetMapping("/diary/detail/{idx}")
    public String diaryDetailPage(@PathVariable("idx") Long idx) {
        DiaryDto diaryDto = diaryService.getOne(idx);
        return "pages/diary/diary_detail";
    }

    // <커플 다이어리 등록>
    @GetMapping("/diary/regist")
    public String diaryRegistPage() {
        return "pages/diary/diary";
    }

    // <커플 다이어리 수정>
    @GetMapping("/diary/modify")
    public String diaryModifyPage() {
        return "pages/diary/diary";
    }
}
