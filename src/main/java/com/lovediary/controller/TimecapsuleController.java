package com.lovediary.controller;
/**
 * 
 * TimecapsuleController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.service.TimeCapsuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TimecapsuleController {
    private TimeCapsuleService timeCapsuleService;
    public TimecapsuleController(TimeCapsuleService service) {
        this.timeCapsuleService = service;
    }
    // <타입캡슐 리스트 페이지>
    @GetMapping("/timecapsule")
    public String timecapsulePage(Model model) {
        model.addAttribute("list", timeCapsuleService.getList());
        return "pages/timecapsule/timecapsule_list";
    }

    // <타입캡슐 상세 페이지>
    @GetMapping("/timecapsule/detail/{idx}")
    public String timecapsuleDetailPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", timeCapsuleService.getOne(idx));
        return "pages/timecapsule/timecapsule_detail";
    }

    // <타입캡슐 등록 페이지>
    @GetMapping("/timecapsule/regist")
    public String timecapsuleRegistPage(Model model) {
        model.addAttribute("detail", new TimecapsuleDto());
        return "pages/timecapsule/timecapsule";
    }

    // <타입캡슐 수정 페이지>
    @GetMapping("/timecapsule/modify/{idx}")
    public String timecapsuleModifyPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", timeCapsuleService.getOne(idx));
        return "pages/timecapsule/timecapsule";
    }
}
