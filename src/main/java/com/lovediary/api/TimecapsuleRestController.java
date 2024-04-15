package com.lovediary.api;

import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.service.TimeCapsuleService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class TimecapsuleRestController {
    private TimeCapsuleService timeCapsuleService;
    public TimecapsuleRestController(TimeCapsuleService service) {
        this.timeCapsuleService = service;
    }

    //타임캡슐 저장
    @PostMapping("/timecapsule/save")
    public ResponseData diarySave(HttpServletRequest request, TimecapsuleDto timeCapsuleDto){
        HttpSession session = request.getSession(true);
        session.getAttribute(constValues.LOGIN_USER);

        if(timeCapsuleDto.getTitle() == null || timeCapsuleDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(timeCapsuleDto.getContents() == null || timeCapsuleDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        if(timeCapsuleDto.getOpenDate() == null) {
            return new ResponseData(constValues.ERROR, "열릴 날짜를 입력해주세요.", null);
        }

        timeCapsuleDto.setAccountIdx(2L);

        timeCapsuleService.saveItem(timeCapsuleDto);

        return new ResponseData(constValues.DONE, "타임캡슐이 저장되었습니다.", null);
    }
}
