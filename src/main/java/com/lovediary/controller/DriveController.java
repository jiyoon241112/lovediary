package com.lovediary.controller;

import com.lovediary.dto.DriveDto;
import com.lovediary.entity.Drive;
import com.lovediary.service.DriveService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * DriveController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
@Controller
public class DriveController extends Session {
    private final DriveService driveService;
    public DriveController(DriveService driveService) {
        this.driveService = driveService;
    }

    @GetMapping("/drive")
    public String drivePage(HttpServletRequest request, Model model) {
        DriveDto driveDto = driveService.getDrive(this.getLoginData(request).getCoupleIdx());

        model.addAttribute("list", driveService.getList(driveDto.getIdx()));

        return "pages/drive/drive";
    }
}
