package com.lovediary.api;

import com.lovediary.dto.TimeCapsuleDto;
import com.lovediary.service.TimeCapsuleService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeCapsuleRestController {
    private TimeCapsuleService timeCapsuleService;
    public TimeCapsuleRestController(TimeCapsuleService service) {
        this.timeCapsuleService = service;
    }

    @PostMapping("/timecapsule/save")
    public ResponseData diarySave(HttpServletRequest request, TimeCapsuleDto timeCapsuleDto){
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
