package com.lovediary.controller;
/**
 * 
 * PlaceController
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
import com.lovediary.service.PlaceService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController extends Session {
    private BookMarkService bookMarkService;
    private PlaceService placeService;
    public PlaceController(BookMarkService service, PlaceService placeService) {
        this.bookMarkService = service;
        this.placeService = placeService;
    }
    
    //데이트 장소 카테고리 리스트 페이지
    @GetMapping("/place")
    public String placePage(HttpServletRequest request, Model model) {
        model.addAttribute("list", bookMarkService.bookMarkList(this.getLoginData(request).getAccountIdx()));

        return "pages/place/place_category";
    }

    //데이트 장소 리스트 페이지
    @GetMapping(value = {"/place/list", "/place/list/{idx}"})
    public String placeListPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                @PathVariable(name = "idx", required = false) Long idx, Model model) {
        model.addAttribute("theme_idx", idx);
        model.addAttribute("keyword", keyword);

        return "pages/place/place_list";
    }

    //데이트 장소 상세 페이지
    @GetMapping("/place/detail/{idx}")
    public String placeDetailPage(HttpServletRequest request,
                                  @PathVariable(name = "idx", required = false) Long idx,
                                  Model model) {
        model.addAttribute("detail", placeService.getOne(idx));

        return "pages/place/place_detail";
    }

    //데이트 장소 지도 페이지
    @GetMapping("/place/map")
    public String placeMapPage() {
        return "pages/place/place_map";
    }
}
