package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {
    @GetMapping("/community")
    public String communityListPage() {
        return "pages/community/community_list";
    }

    @GetMapping("/community/detail")
    public String communityDetailPage() {
        return "pages/community/community_detail";
    }

    @GetMapping("/community/regist")
    public String communityRegistPage() {
        return "pages/community/community";
    }

    @GetMapping("/community/modify")
    public String communityModifyPage() {
        return "pages/community/community";
    }

    @GetMapping("/community/comment")
    public String communityPage() {
        return "pages/community/community_comment";
    }
}
