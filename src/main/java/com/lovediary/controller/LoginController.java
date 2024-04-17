package com.lovediary.controller;

import com.lovediary.dto.AccountDto;
import com.lovediary.service.AccountService;
import com.lovediary.service.CoupleService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * 
 * LoginController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
@Controller
public class LoginController {
    private final AccountService accountService;
    private final CoupleService coupleService;
    public LoginController(AccountService accountService, CoupleService coupleService)
    {
        this.accountService = accountService;
        this.coupleService = coupleService;
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "pages/login/login";
    }

    // 회원가입 페이지
    @GetMapping(value = {"/join", "/join/{page}"})
    public String joinPage(HttpServletRequest request, @PathVariable(name = "page", required = false) Integer page, Model model) {
        if(page == null || page == 1) {
            return "pages/join/join";
        } else if(page == 2) {
            return "pages/join/join2";
        } else if(page == 3) {
            // 커플 고유번호 가져오기
            HttpSession session = request.getSession(true);
            Long idx = (Long) session.getAttribute(constValues.COUPLE_DATA);

            model.addAttribute("couple", coupleService.getOne(idx));

            return "pages/join/join3";
        } else {
            return "pages/join/join4";
        }
    }

    // 비밀번호 찾기 페이지
    @GetMapping(value = {"/find_password", "/find_password/{page}"})
    public String findPasswordPage(@PathVariable(name = "page", required = false) Integer page) {
        if(page == null || page == 1) {
            return "pages/login/find_password";
        } else {
            return "pages/login/find_password2";
        }
    }

    // 내 정보 수정 페이지
    @GetMapping("/profile")
    public String profilePage() {
        return "pages/login/profile";
    }
}
