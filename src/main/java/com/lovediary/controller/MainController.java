package com.lovediary.controller;

import com.lovediary.service.AccountService;
import com.lovediary.service.CoupleService;
import com.lovediary.service.PuppyService;
import com.lovediary.util.Session;
import com.lovediary.values.SessionData;
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
public class MainController extends Session {
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
        SessionData session = this.getLoginData(request);

        model.addAttribute("account", accountService.getOne(session.getAccountIdx()));
        model.addAttribute("partner", accountService.getOne(session.getAccountIdx()));
        model.addAttribute("d_day", coupleService.getDDay(session.getCoupleIdx()));
        model.addAttribute("puppy", puppyService.getOne(session.getCoupleIdx()));

        return "pages/main";
    }
}
