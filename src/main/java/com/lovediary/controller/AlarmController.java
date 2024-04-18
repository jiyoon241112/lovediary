package com.lovediary.controller;

import com.lovediary.dto.AlarmDto;
import com.lovediary.service.AlarmService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
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
public class AlarmController extends Session {
    private final AlarmService alarmService;
    public AlarmController(AlarmService service) {
        this.alarmService = service;
    }

    @GetMapping("/alarm")
    public String alarmPage(HttpServletRequest request,
                            Model model) {
        model.addAttribute("list", alarmService.getList(this.getLoginData(request)));

        return "pages/alarm/alarm";
    }
}
