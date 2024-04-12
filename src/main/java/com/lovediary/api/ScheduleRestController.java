package com.lovediary.api;

import com.lovediary.dto.BucketItemDto;
import com.lovediary.dto.ScheduleDto;
import com.lovediary.service.ScheduleService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * ScheduleRestController
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          JJY             최초 등록
 **/
@RestController
public class ScheduleRestController {
    private ScheduleService scheduleService;
    public ScheduleRestController(ScheduleService service) {
        this.scheduleService = service;
    }

    @PostMapping("/schedule/save")
    public ResponseData bucketSave(HttpServletRequest request, @RequestParam("start_date") String startDate, @RequestParam(value = "end_date", required = false) String endDate, ScheduleDto scheduleDto) throws ParseException {
        HttpSession session = request.getSession(true);
        session.getAttribute(constValues.LOGIN_USER);

        if(scheduleDto.getTitle() == null || scheduleDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(startDate == null) {
            return new ResponseData(constValues.ERROR, "시작일자를 입력해주세요.", null);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        formatter.setLenient(false);

        Date date = formatter.parse(startDate);
        Timestamp timestampStart = new Timestamp(date.getTime());

        Timestamp timestamp = null;
        if(endDate != null && !endDate.isEmpty()) {
            Date closeDate = formatter.parse(endDate);
            timestamp = new Timestamp(closeDate.getTime());
        }

        scheduleDto.setAccountIdx(2L);
        scheduleDto.setStartDate(timestampStart);
        scheduleDto.setEndDate(timestamp);

        scheduleService.saveItem(scheduleDto);

        return new ResponseData(constValues.DONE, "일정이 저장되었습니다.", null);
    }

    @GetMapping( "/schedule/detail/{idx}")
    public ResponseData scheduleDetailPage(@PathVariable("idx") Long idx) {
        return new ResponseData(constValues.DONE, "일정이 저장되었습니다.", scheduleService.getOne(idx));
    }
}
