package com.lovediary.controller;

import com.lovediary.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class ScheduleController {
    private ScheduleService scheduleService;
    public ScheduleController(ScheduleService service) {
        this.scheduleService = service;
    }
    @GetMapping("/schedule")
    public String schedulePage(Model model) {
        model.addAttribute("curr_date", new Timestamp(System.currentTimeMillis()));
        return "pages/schedule/schedule";
    }

    @GetMapping("/schedule/detail")
    public String scheduleDetailPage(@RequestParam(name = "selected") String selectedDate, Model model) {
        model.addAttribute("selected_date", Date.valueOf(selectedDate));
        model.addAttribute("schedule_list", scheduleService.getList(2L, Date.valueOf(selectedDate).toString()));

        return "pages/schedule/schedule_detail";
    }
}
