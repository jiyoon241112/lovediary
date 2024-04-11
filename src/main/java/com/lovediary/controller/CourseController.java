package com.lovediary.controller;

import com.lovediary.service.BookMarkService;
import com.lovediary.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {

    private BookMarkService bookMarkService;
    private CourseService courseService;
    public CourseController(BookMarkService service, CourseService courseService) {
        this.bookMarkService = service;
        this.courseService = courseService;
    }
    @GetMapping("/course")
    public String coursePage(Model model) {
        model.addAttribute("list", bookMarkService.bookMarkList());
        return "pages/course/course_category";
    }

    @GetMapping( value = {"/course/list", "/course/list/{idx}"})
    public String courseListPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                 @PathVariable(name = "idx", required = false) Long idx,Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", courseService.getCourseList(idx, keyword));
        return "pages/course/course_list";
    }

    @GetMapping("/course/detail/{idx}")
    public String courseDetailPage(@PathVariable(name = "idx", required = false) Long idx, Model model) {
        model.addAttribute("detail", courseService.getCourseDetail(idx));
        model.addAttribute("detail_list", courseService.getOne(idx));
        return "pages/course/course_detail";
    }
}
