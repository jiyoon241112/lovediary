package com.lovediary.controller;
/**
 * 
 * HouseholdLedgerController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
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

    //가계부 목록 페이지
    @GetMapping("/household")
    public String householdLedgerPage(Model model, @RequestParam(required = false, name = "type") Character type) {

        model.addAttribute("list", householdLedgerService.getList(type));
        model.addAttribute("amount", householdLedgerService.totalAmount());
        model.addAttribute("type", type);
        return "pages/household_ledger/household_ledger";
    }

    //가계부 상세 페이지
    @GetMapping("/household/detail")
    public String householdLedgerDetailPage(Model model) {
        model.addAttribute("monthAmount", householdLedgerService.monthTotal());
        model.addAttribute("totalAmount", householdLedgerService.monthTotalAmount());
        return "pages/household_ledger/household_ledger_detail";
    }

    //가계부 등록 페이지
    @GetMapping("/household/regist")
    public String householdLedgerRegistPage(Model model) {
        model.addAttribute("detail", new HouseholdLedgerDto());
        return "pages/household_ledger/household_ledger_edit";
    }

    //가계부 수정 페이지
    @GetMapping("/household/modify/{idx}")
    public String householdLedgerModifyPage(@PathVariable("idx") Long idx, Model model) {
        model.addAttribute("detail", householdLedgerService.getOne(idx));
        return "pages/household_ledger/household_ledger_edit";
    }
}
