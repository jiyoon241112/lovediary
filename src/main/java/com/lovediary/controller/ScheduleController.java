package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Timestamp;

@Controller
public class ScheduleController {
    @GetMapping("/schedule")
    public String schedulePage(Model model) {
        model.addAttribute("curr_date", new Timestamp(System.currentTimeMillis()));
        return "pages/schedule/schedule";
    }

    @GetMapping("/schedule/detail")
    public String scheduleDetailPage() {
        return "pages/schedule/schedule_detail";
    }
}
