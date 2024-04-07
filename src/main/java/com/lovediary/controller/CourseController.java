package com.lovediary.controller;

import com.lovediary.service.BookMarkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class CourseController {

    private BookMarkService bookMarkService;
    public CourseController(BookMarkService service) {
        this.bookMarkService = service;
    }
    @GetMapping("/course")
    public String coursePage(Model model) {
        model.addAttribute("list", bookMarkService.bookMarkList());
        return "pages/course/course_category";
    }

    @GetMapping("/course/list")
    public String courseListPage() {
        return "pages/course/course_list";
    }

    @GetMapping("/course/detail")
    public String courseDetailPage() {
        return "pages/course/course_detail";
    }
}
