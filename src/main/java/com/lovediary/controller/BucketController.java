package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BucketController {
    @GetMapping("/bucket")
    public String bucketListPage() {
        return "pages/bucket/bucket_list";
    }

    @GetMapping("/bucket/detail")
    public String bucketDetailPage() {
        return "pages/bucket/bucket_detail";
    }

    @GetMapping("/bucket/regist")
    public String bucketRegistPage() {
        return "pages/bucket/bucket";
    }

    @GetMapping("/bucket/modify")
    public String bucketModifyPage() {
        return "pages/bucket/bucket";
    }

    @GetMapping("/bucket/item")
    public String bucketItemPage() {
        return "pages/bucket/bucket_item";
    }
}
