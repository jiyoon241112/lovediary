package com.lovediary.controller;

import com.lovediary.dto.AlarmDto;
import com.lovediary.service.AlarmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 
 * AlarmController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-26
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-26          HTH             최초 등록
 **/
@Controller
public class AlarmController {
    private AlarmService alarmService;
    public AlarmController(AlarmService service) {
        this.alarmService = service;
    }

    @GetMapping("/alarm")
    public String alarmPage(Model model) {
        model.addAttribute("list", alarmService.getList());
        return "pages/alarm/alarm";
    }

    @PutMapping("/alarm/read/{idx}")
    public String alarmRead(@PathVariable("idx") Long idx) {
        AlarmDto alarmDto = alarmService.getOne(idx);
        alarmDto.setReadYn('Y');
        return "redirect:/";
    }
}
