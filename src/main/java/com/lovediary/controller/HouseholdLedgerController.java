package com.lovediary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HouseholdLedgerController {
    @GetMapping("/household")
    public String householdLedgerPage() {
        return "pages/household_ledger/household_ledger";
    }

    @GetMapping("/household/detail")
    public String householdLedgerDetailPage() {
        return "pages/household_ledger/household_ledger_detail";
    }

    @GetMapping("/household/regist")
    public String householdLedgerRegistPage() {
        return "pages/household_ledger/household_ledger_edit";
    }

    @GetMapping("/household/modify")
    public String householdLedgerModifyPage() {
        return "pages/household_ledger/household_ledger_edit";
    }
}
