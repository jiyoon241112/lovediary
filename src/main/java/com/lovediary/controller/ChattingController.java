package com.lovediary.controller;

import com.lovediary.values.SessionData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * ChattingController
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
public class ChattingController {
    @GetMapping("/chatting")
    public String chattingPage(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        SessionData sessionData = (SessionData) session.getAttribute(constValues.LOGIN_USER);
//
//        model.addAttribute("couple_idx", sessionData.getCoupleIdx());
//        model.addAttribute("account_idx", sessionData.getAccountIdx());

        model.addAttribute("couple_idx", 1L);
        model.addAttribute("account_idx", 2L);

        return "pages/chatting/chatting";
    }
}
