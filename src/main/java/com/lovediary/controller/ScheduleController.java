package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/schedule")
    public String schedulePage() {
        return "pages/schedule/schedule";
    }

    @GetMapping("/schedule/detail")
    public String scheduleDetailPage() {
        return "pages/schedule/schedule_detail";
    }
}
