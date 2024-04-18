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
import com.lovediary.dto.ScheduleDto;
import com.lovediary.service.ScheduleService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class ScheduleController extends Session {
    private ScheduleService scheduleService;
    public ScheduleController(ScheduleService service) {
        this.scheduleService = service;
    }
    
    // 일정 페이지
    @GetMapping("/schedule")
    public String schedulePage(Model model) {
        model.addAttribute("curr_date", new Timestamp(System.currentTimeMillis()));

        return "pages/schedule/schedule";
    }

    // 일정 상세 페이지
    @GetMapping("/schedule/detail")
    public String scheduleDetailPage(HttpServletRequest request,
                                     @RequestParam(name = "selected") String selectedDate,
                                     Model model) {
        model.addAttribute("selected_date", Date.valueOf(selectedDate));
        model.addAttribute("schedule_list", scheduleService.getList(Date.valueOf(selectedDate).toString(), this.getLoginData(request)));

        return "pages/schedule/schedule_detail";
    }

    // 일정 등록 페이지
    @GetMapping("/schedule/regist")
    public String scheduleRegistPage(HttpServletRequest request, Model model) {
        model.addAttribute("schedule", new ScheduleDto());
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/schedule/schedule_regist";
    }

    // 일정 수정 페이지
    @GetMapping("/schedule/modify/{idx}")
    public String scheduleRegistPage(HttpServletRequest request,
                                     @PathVariable("idx") Long idx,
                                     Model model) {
        model.addAttribute("schedule", scheduleService.getOne(idx));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/schedule/schedule_regist";
    }
}
