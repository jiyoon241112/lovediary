package com.lovediary.controller;

import com.lovediary.service.BookMarkService;
import com.lovediary.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {
    private BookMarkService bookMarkService;
    private PlaceService placeService;
    public PlaceController(BookMarkService service, PlaceService placeService) {
        this.bookMarkService = service;
        this.placeService = placeService;
    }
    @GetMapping("/place")
    public String placePage(Model model) {
        model.addAttribute("list", bookMarkService.bookMarkList());
        return "pages/place/place_category";
    }

    @GetMapping(value = {"/place/list", "/place/list/{idx}"})
    public String placeListPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                @PathVariable(name = "idx", required = false) Long idx, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", placeService.getPlaceList(idx, keyword));
        if(true) {
            return "pages/place/place_list";
        } else {
            return "pages/place/place_list_frame";
        }
    }

    @GetMapping("/place/detail/{idx}")
    public String placeDetailPage(@PathVariable(name = "idx", required = false) Long idx, Model model) {
        model.addAttribute("detail", placeService.getOne(idx));
        return "pages/place/place_detail";
    }

    @GetMapping("/place/map")
    public String placeMapPage() {
        return "pages/place/place_map";
    }
}
