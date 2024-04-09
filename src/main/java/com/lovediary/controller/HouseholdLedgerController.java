package com.lovediary.controller;

import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.service.HouseholdLedgerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HouseholdLedgerController {
    private HouseholdLedgerService householdLedgerService;
    public HouseholdLedgerController(HouseholdLedgerService service) {
        this.householdLedgerService = service;
    }

    @GetMapping("/household")
    public String householdLedgerPage(Model model, @RequestParam(required = false, name = "type") Character type) {

        model.addAttribute("list", householdLedgerService.getList(type));
        model.addAttribute("amount", householdLedgerService.totalAmount());
        model.addAttribute("type", type);
        return "pages/household_ledger/household_ledger";
    }

    @GetMapping("/household/detail")
    public String householdLedgerDetailPage(Model model) {
//        HouseholdLedgerDto householdLedgerDto = householdLedgerService.getOne(idx);
        model.addAttribute("monthAmount", householdLedgerService.monthTotal());
        model.addAttribute("totalAmount", householdLedgerService.monthTotalAmount());
        return "pages/household_ledger/household_ledger_detail";
    }

    @GetMapping("/household/regist")
    public String householdLedgerRegistPage(Model model) {
        model.addAttribute("detail", new HouseholdLedgerDto());
        return "pages/household_ledger/household_ledger_edit";
    }

    @GetMapping("/household/modify/{idx}")
    public String householdLedgerModifyPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", householdLedgerService.getOne(idx));
        return "pages/household_ledger/household_ledger_edit";
    }
}
