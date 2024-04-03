package com.lovediary.api;

import com.lovediary.dto.DiaryDto;
import com.lovediary.repository.DiaryRepository;
import com.lovediary.service.DiaryService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryRestController {
    private DiaryService diaryService;
    public DiaryRestController(DiaryService service) {
        this.diaryService = service;
    }

    @PostMapping("/diary/save")
    public ResponseData diarySave(HttpServletRequest request, DiaryDto diaryDto){
        HttpSession session = request.getSession(true);
        session.getAttribute(constValues.LOGIN_USER);

        if(diaryDto.getTitle() == null || diaryDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(diaryDto.getContents() == null || diaryDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        diaryDto.setAccountIdx(2L);
        diaryDto.setCoupleIdx(1L);

        diaryService.saveItem(diaryDto);

        return new ResponseData(constValues.DONE, "오늘의 일기가 저장되었습니다.", null);
    }
}
