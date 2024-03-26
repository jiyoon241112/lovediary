package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceController {
    @GetMapping("/place")
    public String placePage() {
        return "pages/place/place_category";
    }

    @GetMapping("/place/list")
    public String placeListPage() {
        if(true) {
            return "pages/place/place_list";
        } else {
            return "pages/place/place_list_frame";
        }
    }

    @GetMapping("/place/detail")
    public String placeDetailPage() {
        return "pages/place/place_detail";
    }

    @GetMapping("/place/map")
    public String placeMapPage() {
        return "pages/place/place_map";
    }
}
