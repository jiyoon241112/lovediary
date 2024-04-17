package com.lovediary.api;

import com.lovediary.dto.*;
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
import java.util.List;

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

    //스케줄 저장
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

    //일정 여부 조회
    @GetMapping("/schedule/check")
    public ResponseData getList(@RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        List<CalendarListDto> resultList = scheduleService.getCalendarList();
        return new ResponseData(constValues.DONE, "조회했습니다.", resultList);
    }

    // 일정 삭제
    @PostMapping("/schedule/delete")
    public ResponseData deleteSchedule(HttpServletRequest request, @RequestParam(name = "idx") Long idx) {
        if(idx == null) {
            return new ResponseData(constValues.ERROR, "삭제할 일정이 없습니다.", null);
        }

        ScheduleDto scheduleDto = scheduleService.getOne(idx);
        scheduleDto.setDeleteYn('Y');
        scheduleDto.setDeleteDate(new Timestamp(System.currentTimeMillis()));

        Long result = scheduleService.saveItem(scheduleDto);

        return new ResponseData(constValues.DONE, "일정이 삭제되었습니다.", result);
    }
}
