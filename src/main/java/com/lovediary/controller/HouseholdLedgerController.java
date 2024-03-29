package com.lovediary.controller;

import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.service.HouseholdLedgerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HouseholdLedgerController {
    private HouseholdLedgerService householdLedgerService;
    public HouseholdLedgerController(HouseholdLedgerService service) {
        this.householdLedgerService = service;
    }

    @GetMapping("/household")
    public String householdLedgerPage(Model model) {
        model.addAttribute("list", householdLedgerService.getList());
        return "pages/household_ledger/household_ledger";
    }

    @GetMapping("/household/detail/{idx}")
    public String householdLedgerDetailPage(@PathVariable("idx") Long idx) {
        HouseholdLedgerDto householdLedgerDto = householdLedgerService.getOne(idx);
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
