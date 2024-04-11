package com.lovediary.controller;

import com.lovediary.dto.BalanceAnswerDto;
import com.lovediary.dto.BalanceDto;
import com.lovediary.dto.BalanceItemDto;
import com.lovediary.dto.BalanceReplyDto;
import com.lovediary.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 *
 * BalanceController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
@Controller
public class BalanceController {
    private final BalanceService balanceService;
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/balance")
    public String balanceListPage(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword, Model model) {
        model.addAttribute("keyword", keyword);
        model.addAttribute("list", balanceService.getList(keyword));
        return "pages/balance/balance_list";
    }

    @GetMapping("/balance/detail/{idx}")
    public String balanceDetailPage(@PathVariable(name = "idx") Long idx, Model model) {
        BalanceAnswerDto answer = balanceService.getAnswer(idx, 1L);
        Long selectedIdx = null;
        if(answer != null) {
            selectedIdx = answer.getBalanceItemIdx();
        }

        model.addAttribute("balance", balanceService.getOne(idx));
        model.addAttribute("item_list", balanceService.getItemList(idx));
        model.addAttribute("selected_idx", selectedIdx);
        model.addAttribute("comment_list", balanceService.getCommentList(idx, null));
        return "pages/balance/balance_detail";
    }

    @GetMapping("/balance/regist")
    public String balanceRegistPage(Model model) {
        model.addAttribute("balance", new BalanceDto());
        model.addAttribute("item_list", new ArrayList<BalanceItemDto>());
        return "pages/balance/balance";
    }

    @GetMapping("/balance/modify/{idx}")
    public String balanceModifyPage(@PathVariable(name = "idx") Long idx, Model model) {
        model.addAttribute("balance", balanceService.getOne(idx));
        model.addAttribute("item_list", balanceService.getItemList(idx));
        return "pages/balance/balance";
    }

    @GetMapping("/balance/comment/{idx}")
    public String balanceCommentPage(@PathVariable(name = "idx") Long idx, Model model) {
        BalanceReplyDto comment = balanceService.getCommentOne(idx);

        model.addAttribute("comment", comment);
        model.addAttribute("list", balanceService.getCommentList(comment.getBalanceIdx(), idx));
        return "pages/balance/balance_comment";
    }
}
