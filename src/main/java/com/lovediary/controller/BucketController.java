package com.lovediary.controller;

import com.lovediary.dto.BucketDto;
import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BucketController {
    private BucketService bucketService;
    public BucketController(BucketService service) {
        this.bucketService = service;
    }
    @GetMapping("/bucket")
    public String bucketListPage(Model model) {
        model.addAttribute("list", bucketService.getList());
        return "pages/bucket/bucket_list";
    }

    @GetMapping("/bucket/detail/{idx}")
    public String bucketDetailPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", bucketService.getOne(idx));
        model.addAttribute("detail_item", bucketService.getBucketItemList(idx));
        return "pages/bucket/bucket_detail";
    }

    @GetMapping("/bucket/regist")
    public String bucketRegistPage(Model model) {
        model.addAttribute("detail", new BucketDto());
        return "pages/bucket/bucket";
    }

    @GetMapping("/bucket/modify/{idx}")
    public String bucketModifyPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", bucketService.getOne(idx));
        return "pages/bucket/bucket";
    }

    @GetMapping("/bucket/item/{idx}")
    public String bucketItemPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("idx", idx);
        model.addAttribute("detail", bucketService.getItemOne(idx));
        return "pages/bucket/bucket_item";
    }
}
