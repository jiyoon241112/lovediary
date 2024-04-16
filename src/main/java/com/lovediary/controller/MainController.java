package com.lovediary.controller;

import com.lovediary.service.AccountService;
import com.lovediary.service.CoupleService;
import com.lovediary.service.PuppyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * MainController
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
public class MainController {
    private final AccountService accountService;
    private final CoupleService coupleService;
    private final PuppyService puppyService;
    public MainController(AccountService accountService, CoupleService coupleService, PuppyService puppyService) {
        this.accountService = accountService;
        this.coupleService = coupleService;
        this.puppyService = puppyService;
    }

    @GetMapping(value = {"", "/"})
    public String mainPage(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        SessionData sessionData = (SessionData) session.getAttribute(constValues.LOGIN_USER);
//
//        model.addAttribute("couple_idx", sessionData.getCoupleIdx());
//        model.addAttribute("account_idx", sessionData.getAccountIdx());

        model.addAttribute("account", accountService.getOne(2L));
        model.addAttribute("partner", accountService.getOne(3L));
        model.addAttribute("d_day", coupleService.getDDay(1L));
        model.addAttribute("puppy", puppyService.getOne(1L));

        return "pages/main";
    }
}
