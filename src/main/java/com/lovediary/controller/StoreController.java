package com.lovediary.controller;

import com.lovediary.service.CoupleService;
import com.lovediary.service.PointService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    private final CoupleService coupleService;
    private final PointService pointService;
    public StoreController(CoupleService coupleService, PointService pointService) {
        this.coupleService = coupleService;
        this.pointService = pointService;
    }

    @GetMapping("/store")
    public String storePage(HttpServletRequest request, Model model) {
        model.addAttribute("point", coupleService.getOne(1L).getPoint());
        return "pages/store/store";
    }

    @GetMapping("/point_history")
    public String pointHistoryPage(HttpServletRequest request, Model model) {
        model.addAttribute("point", coupleService.getOne(1L).getPoint());
        model.addAttribute("list", pointService.getList());
        return "pages/store/point_history";
    }
}
