package com.lovediary.controller;
/**
 * 
 * DiaryController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
import com.lovediary.service.DiaryService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiaryController extends Session {
    private DiaryService diaryService;
    public DiaryController(DiaryService service) {
        this.diaryService = service;
    }

    // <커플 다이어리 리스트 페이지>
    @GetMapping("/diary")
    public String diaryPage(HttpServletRequest request, Model model) {
        model.addAttribute("list", diaryService.getList(this.getLoginData(request)));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/diary/diary_list";
    }

    // <커플 다이어리 상세 페이지>
    @GetMapping("/diary/detail/{idx}")
    public String diaryDetailPage(HttpServletRequest request,
                                  @PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", diaryService.getOne(idx));
        model.addAttribute("comment_list", diaryService.getDiaryCommentList(idx));
        model.addAttribute("session_data", this.getLoginData(request));

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
