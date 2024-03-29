package com.lovediary.controller;

import com.lovediary.dto.TimeCapsuleDto;
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
    public String timecapsuleDetailPage(@PathVariable("idx") Long idx) {
        TimeCapsuleDto timecapsuleDto = timeCapsuleService.getOne(idx);
        return "pages/timecapsule/timecapsule_detail";
    }

    // <타입캡슐 등록 페이지>
    @GetMapping("/timecapsule/regist")
    public String timecapsuleRegistPage() {
        return "pages/timecapsule/timecapsule";
    }

    // <타입캡슐 수정 페이지>
    @GetMapping("/timecapsule/modify")
    public String timecapsuleModifyPage() {
        return "pages/timecapsule/timecapsule";
    }
}
