package com.lovediary.controller;
/**
 * 
 * BucketController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
import com.lovediary.dto.BucketDto;
import com.lovediary.dto.BucketItemDto;
import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.service.BucketService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BucketController extends Session {
    private BucketService bucketService;
    public BucketController(BucketService service) {
        this.bucketService = service;
    }
    //버킷리스트 목록 페이지
    @GetMapping("/bucket")
    public String bucketListPage(HttpServletRequest request, Model model) {
        model.addAttribute("list", bucketService.getList(this.getLoginData(request)));

        return "pages/bucket/bucket_list";
    }

    //버킷리스트 상세 페이지
    @GetMapping("/bucket/detail/{idx}")
    public String bucketDetailPage(HttpServletRequest request,
                                   @PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", bucketService.getOne(idx));
        model.addAttribute("detail_item", bucketService.getBucketItemList(idx));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/bucket/bucket_detail";
    }

    //버킷리스트 등록 페이지
    @GetMapping("/bucket/regist")
    public String bucketRegistPage(Model model) {
        model.addAttribute("detail", new BucketDto());

        return "pages/bucket/bucket";
    }

    //버킷리스트 수정 페이지
    @GetMapping("/bucket/modify/{idx}")
    public String bucketModifyPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", bucketService.getOne(idx));

        return "pages/bucket/bucket";
    }

    //버킷리스트 항목 페이지
    @GetMapping( value = {"/bucket/item", "/bucket/item/{idx}"})
    public String bucketItemPage(@PathVariable(name = "idx", required = false) Long idx,
                                 @RequestParam(name="bucket_idx", required = false) Long bucketIdx,
                                 Model model) {
        BucketItemDto bucketItem = new BucketItemDto();

        if(idx != null) {
            bucketItem = bucketService.getItemOne(idx);
        }
        model.addAttribute("bucket_idx", bucketIdx);
        model.addAttribute("detail", bucketItem);

        return "pages/bucket/bucket_item";
    }
}
