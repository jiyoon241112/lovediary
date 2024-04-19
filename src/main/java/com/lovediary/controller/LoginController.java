package com.lovediary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lovediary.dto.AccountDto;
import com.lovediary.service.AccountService;
import com.lovediary.service.CoupleService;
import com.lovediary.service.NaverService;
import com.lovediary.util.Session;
import com.lovediary.values.ResponseData;
import com.lovediary.values.SessionData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
public class LoginController extends Session {
    private final NaverService naverService;
    private final AccountService accountService;
    private final CoupleService coupleService;
    public LoginController(NaverService naverService, AccountService accountService, CoupleService coupleService)
    {
        this.naverService = naverService;
        this.accountService = accountService;
        this.coupleService = coupleService;
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "err", required = false) String errMsg, Model model) {
        model.addAttribute("err", errMsg);

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
            return "pages/join/join3";
        } else {
            // 커플 고유번호 가져오기
            HttpSession session = this.getSessionData(request);
            Long idx = (Long) session.getAttribute(constValues.COUPLE_DATA);

            model.addAttribute("couple", coupleService.getOne(idx));

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

    // 네이버 로그인
    @GetMapping("/login/naver")
    public void naverLogin(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String url = naverService.getLoginUri();

        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 네이버 로그인 콜백
    @GetMapping("/naver/do")
    public String naverLoginCB(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "code") String code,
                                     @RequestParam(value = "state") String state,
                                     @RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "error_description", required = false) String errorMsg) throws UnsupportedEncodingException, JsonProcessingException {
        if (error != null) {
            return "redirect:/login?error=" + errorMsg;
        }

        HttpSession session = this.getSessionData(request);

        try {
            Map<String, Object> map = naverService.getLoginToken(code, state);
            Map<String, Object> loginInfo = naverService.getNaverUserInfo(map);

            loginInfo = (Map<String, Object>) loginInfo.get("response");
            SessionData sessionData = accountService.getByNaverLogin(loginInfo);

            if(sessionData != null) {
                String birtDay = ((String) loginInfo.get("birthyear")) + "-" + ((String) loginInfo.get("birthday"));
                AccountDto accountDto = AccountDto.builder()
                        .name((String)loginInfo.get("name"))
                        .loveName((String)loginInfo.get("nickname"))
                        .gender(((String) loginInfo.get("gender")).equals("F") ? 'W' : 'M')
                        .email((String) loginInfo.get("email"))
                        .phoneNumber((String) loginInfo.get("phone"))
                        .birthDay(Date.valueOf(birtDay))
                        .build();

                session.setAttribute(constValues.JOIN_DATA, accountDto);
                return "redirect:/join/3";
            }

            // 로그인 정보를 세션에 저장
            session.setAttribute(constValues.LOGIN_USER, sessionData);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/join";
        }
    }
}
