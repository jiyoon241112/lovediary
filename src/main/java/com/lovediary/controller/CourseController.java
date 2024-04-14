package com.lovediary.controller;
/**
 * 
 * CourseController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
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

    // 데이트 코스 카테고리 페이지
    @GetMapping("/course")
    public String coursePage(Model model) {
        model.addAttribute("list", bookMarkService.bookMarkList());
        return "pages/course/course_category";
    }

    //데이트 코스 리스트 페이지
    @GetMapping( value = {"/course/list", "/course/list/{idx}"})
    public String courseListPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                 @PathVariable(name = "idx", required = false) Long idx,Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", courseService.getCourseList(idx, keyword));
        return "pages/course/course_list";
    }

    //데이트 코스 상세 페이지
    @GetMapping("/course/detail/{idx}")
    public String courseDetailPage(@PathVariable(name = "idx", required = false) Long idx, Model model) {
        model.addAttribute("detail", courseService.getCourseDetail(idx));
        model.addAttribute("detail_list", courseService.getOne(idx));
        return "pages/course/course_detail";
    }
}
