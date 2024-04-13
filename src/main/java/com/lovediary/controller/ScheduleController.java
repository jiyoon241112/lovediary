package com.lovediary.controller;
/**
 * 
 * ScheduleController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
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
    
    //데이트 장소 달력 페이지
    @GetMapping("/schedule")
    public String schedulePage(Model model) {
        model.addAttribute("curr_date", new Timestamp(System.currentTimeMillis()));
        return "pages/schedule/schedule";
    }

    //데이트 장소 달력 상세 페이지
    @GetMapping("/schedule/detail")
    public String scheduleDetailPage(@RequestParam(name = "selected") String selectedDate, Model model) {
        model.addAttribute("selected_date", Date.valueOf(selectedDate));
        model.addAttribute("schedule_list", scheduleService.getList(2L, Date.valueOf(selectedDate).toString()));

        return "pages/schedule/schedule_detail";
    }
}
