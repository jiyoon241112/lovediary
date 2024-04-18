package com.lovediary.api;

import com.lovediary.dto.DiaryCommentDto;
import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.service.TimeCapsuleService;
import com.lovediary.util.Session;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * 
 * TimeCapsuleRestController
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
public class TimecapsuleRestController extends Session {
    private TimeCapsuleService timeCapsuleService;
    public TimecapsuleRestController(TimeCapsuleService service) {
        this.timeCapsuleService = service;
    }

    //타임캡슐 저장
    @PostMapping("/timecapsule/save")
    public ResponseData diarySave(HttpServletRequest request,
                                  TimecapsuleDto timeCapsuleDto){
        if(timeCapsuleDto.getTitle() == null || timeCapsuleDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(timeCapsuleDto.getContents() == null || timeCapsuleDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        if(timeCapsuleDto.getOpenDate() == null) {
            return new ResponseData(constValues.ERROR, "열릴 날짜를 입력해주세요.", null);
        }

        timeCapsuleDto.setAccountIdx(this.getLoginData(request).getAccountIdx());

        timeCapsuleService.saveItem(timeCapsuleDto);

        return new ResponseData(constValues.DONE, "타임캡슐이 저장되었습니다.", null);
    }

    // 타임캡슐 삭제
    @PostMapping("/timecapsule/delete")
    public ResponseData deleteComment(HttpServletRequest request,
                                      @RequestParam(name = "idx") Long idx) {
        if(idx == null) {
            return new ResponseData(constValues.ERROR, "삭제할 타임캡슐이 없습니다.", null);
        }

        TimecapsuleDto timecapsuleDto = timeCapsuleService.getOne(idx);
        timecapsuleDto.setDeleteYn('Y');
        timecapsuleDto.setDeleteDate(new Timestamp(System.currentTimeMillis()));

        Long result = timeCapsuleService.saveItem(timecapsuleDto);

        return new ResponseData(constValues.DONE, "타임캡슐이 삭제되었습니다.", result);
    }
}
