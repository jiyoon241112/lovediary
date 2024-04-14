package com.lovediary.api;

import com.lovediary.dto.BookMarkPlaceDto;
import com.lovediary.service.PlaceService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * PlaceRestController
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-14
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-14          JJY             최초 등록
 **/
@RestController
public class PlaceRestController {
    private final PlaceService placeService;
    public PlaceRestController(PlaceService placeService) {
        this.placeService = placeService;
    }

    // 장소 리스트 조회
    @GetMapping("/place/get_place")
    public ResponseData getList(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                @RequestParam(name = "theme_idx", required = false) Long themeIdx,
                                @RequestParam(name = "latitude", required = false) Float latitude,
                                @RequestParam(name = "longitude", required = false) Float longitude) {

        List<BookMarkPlaceDto> resultList = placeService.getPlaceList(themeIdx, keyword, latitude, longitude);
        return new ResponseData(constValues.DONE, "조회했습니다.", resultList);
    }
}
