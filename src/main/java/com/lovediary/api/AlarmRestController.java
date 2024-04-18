package com.lovediary.api;

import com.lovediary.dto.AlarmDto;
import com.lovediary.service.AlarmService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 *
 * AlarmRestController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-18
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-18          HTH             최초 등록
 **/
@RestController
public class AlarmRestController {
    private final AlarmService alarmService;
    public AlarmRestController(AlarmService service) {
        this.alarmService = service;
    }

    @PostMapping("/alarm/read/{idx}")
    public ResponseData alarmRead(@PathVariable("idx") Long idx) {
        AlarmDto alarmDto = alarmService.getOne(idx);
        alarmDto.setReadYn('Y');
        alarmDto.setReadDate(new Timestamp(System.currentTimeMillis()));

        alarmService.saveItem(alarmDto);

        return new ResponseData(constValues.DONE, "저장되었습니다.", null);
    }
}
