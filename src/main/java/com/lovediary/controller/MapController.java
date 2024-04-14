package com.lovediary.controller;
/**
 * 
 * MapController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MapController {
    @GetMapping("/map")
    public String mapPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        return "pages/search_map";
    }
}
