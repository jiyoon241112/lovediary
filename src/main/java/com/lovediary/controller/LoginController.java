package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        return "pages/login/login";
    }

    @GetMapping("/join")
    public String joinPage() {
        if(true) {
            return "pages/login/join";
        } else {
            return "pages/login/join2";
        }
    }

    @GetMapping("/find_password")
    public String findPasswordPage() {
        if(true) {
            return "pages/login/find_password";
        } else {
            return "pages/login/find_password2";
        }
    }
}
