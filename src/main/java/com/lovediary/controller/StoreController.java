package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    @GetMapping("/store")
    public String storePage() {
        return "pages/store/store";
    }

    @GetMapping("/point_history")
    public String pointHistoryPage() {
        return "pages/store/point_history";
    }
}
