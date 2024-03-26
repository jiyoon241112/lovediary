package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
    @GetMapping("/course")
    public String coursePage() {
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
