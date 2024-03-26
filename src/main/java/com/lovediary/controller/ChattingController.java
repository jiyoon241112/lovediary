package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChattingController {
    @GetMapping("/chatting")
    public String chattingPage() {
        return "pages/chatting/chatting";
    }
}
