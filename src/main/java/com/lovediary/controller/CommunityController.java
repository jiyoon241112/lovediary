package com.lovediary.controller;

import com.lovediary.dto.CommunityDto;
import com.lovediary.dto.CommunityReplyDto;
import com.lovediary.service.CommunityService;
import com.lovediary.util.Session;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * CommunityController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Controller
public class CommunityController extends Session {
    private final CommunityService communityService;
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 커뮤니티 목록 페이지
    @GetMapping("/community")
    public String communityListPage(HttpServletRequest request,
                                    @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
                                    Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", communityService.getList(keyword));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/community/community_list";
    }

    // 커뮤니티 상세 페이지
    @GetMapping("/community/detail/{idx}")
    public String communityDetailPage(HttpServletRequest request,
                                      @PathVariable(name = "idx") Long idx,
                                      Model model) {
        model.addAttribute("community", communityService.getOne(idx));
        model.addAttribute("comment_list", communityService.getCommentList(idx, null));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/community/community_detail";
    }

    // 커뮤니티 등록 페이지
    @GetMapping("/community/regist")
    public String communityRegistPage(Model model) {
        model.addAttribute("community", new CommunityDto());

        return "pages/community/community";
    }

    // 커뮤니티 수정 페이지
    @GetMapping("/community/modify/{idx}")
    public String communityModifyPage(@PathVariable(name = "idx") Long idx,
                                      Model model) {
        model.addAttribute("community", communityService.getOne(idx));

        return "pages/community/community";
    }
    
    // 커뮤니티 댓글 페이지
    @GetMapping("/community/comment/{idx}")
    public String communityPage(HttpServletRequest request,
                                @PathVariable(name = "idx") Long idx,
                                Model model) {
        CommunityReplyDto comment = communityService.getCommentOne(idx);

        model.addAttribute("comment", comment);
        model.addAttribute("list", communityService.getCommentList(comment.getCommunityIdx(), idx));
        model.addAttribute("session_data", this.getLoginData(request));

        return "pages/community/community_comment";
    }
}
