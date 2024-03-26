package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalanceController {
    @GetMapping("/balance")
    public String balanceListPage() {
        return "pages/balance/balance_list";
    }

    @GetMapping("/balance/detail")
    public String balanceDetailPage() {
        return "pages/balance/balance_detail";
    }

    @GetMapping("/balance/regist")
    public String balanceRegistPage() {
        return "pages/balance/balance";
    }

    @GetMapping("/balance/modify")
    public String balanceModifyPage() {
        return "pages/balance/balance";
    }

    @GetMapping("/balance/comment")
    public String balanceCommentPage() {
        return "pages/balance/balance_comment";
    }
}
