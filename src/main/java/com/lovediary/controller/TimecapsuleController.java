package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimecapsuleController {
    @GetMapping("/timecapsule")
    public String timecapsulePage() {
        return "pages/timecapsule/timecapsule_list";
    }

    @GetMapping("/timecapsule/detail")
    public String timecapsuleDetailPage() {
        return "pages/timecapsule/timecapsule_detail";
    }

    @GetMapping("/timecapsule/regist")
    public String timecapsuleRegistPage() {
        return "pages/timecapsule/timecapsule";
    }

    @GetMapping("/timecapsule/modify")
    public String timecapsuleModifyPage() {
        return "pages/timecapsule/timecapsule";
    }
}
