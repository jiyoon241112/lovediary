package com.lovediary.api;

import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.service.HouseholdLedgerService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * HouseholdRestController
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
@RestController
public class HouseholdRestController {
    private HouseholdLedgerService householdLedgerService;

    public HouseholdRestController(HouseholdLedgerService householdLedgerService){this.householdLedgerService = householdLedgerService;}

    @PostMapping("/household/save")
    public ResponseData timeCapsuleSave(HttpServletRequest request, @RequestParam(name = "due_date") String dueDate, HouseholdLedgerDto householdLedgerDto){
        HttpSession session = request.getSession(true);
        session.getAttribute(constValues.LOGIN_USER);

        if(dueDate == null || dueDate.isEmpty()) {
            return new ResponseData(constValues.ERROR, "발생일을 입력해주세요.", null);
        }

        if(householdLedgerDto.getType() == null) {
            return new ResponseData(constValues.ERROR, "입금/지출 여부를 입력해주세요.", null);
        }

        if(householdLedgerDto.getAmount() == null) {
            return new ResponseData(constValues.ERROR, "금액을 입력해주세요.", null);
        }

        if(householdLedgerDto.getContents() == null || householdLedgerDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

//        if(householdLedgerDto.getCategoryIdx() == null) {
//            return new ResponseData(constValues.ERROR, "가계부 카테고리를 입력해주세요.", null);
//        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parseDueDate = dateFormat.parse(dueDate);

            householdLedgerDto.setDueDate(new java.sql.Timestamp(parseDueDate.getTime()));
        } catch (ParseException e) {
            householdLedgerDto.setDueDate(null);
        }

        householdLedgerDto.setAccountIdx(2L);
        householdLedgerDto.setCategoryIdx(1L);

        householdLedgerService.saveItem(householdLedgerDto);

        return new ResponseData(constValues.DONE, "가계부가 저장되었습니다.", null);
    }

    @GetMapping("/household/chart")
    public ResponseData getChartData() {
        return new ResponseData(constValues.DONE, "차트 데이터를 조회했습니다.", householdLedgerService.monthTotalAmount());
    }
}
