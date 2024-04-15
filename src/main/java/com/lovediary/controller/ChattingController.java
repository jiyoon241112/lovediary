package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingController {
    @GetMapping("/chatting")
    public String chattingPage(Model model) {
        model.addAttribute("couple_idx", 1L);

        return "pages/chatting/chatting";
    }
}
