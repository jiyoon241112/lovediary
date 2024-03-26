package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriveController {
    @GetMapping("/drive")
    public String drivePage() {
        return "pages/drive/drive";
    }
}
