package com.lovediary.api;

import com.lovediary.dto.DiaryDto;
import com.lovediary.repository.DiaryRepository;
import com.lovediary.service.DiaryService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * DiaryRestController
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
public class DiaryRestController {
    private DiaryService diaryService;
    public DiaryRestController(DiaryService service) {
        this.diaryService = service;
    }

    //다이어리 저장
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

    // 댓글 저장
    @PostMapping("/diary/save_comment")
    public ResponseData saveComment(HttpServletRequest request, @RequestParam(name = "couple_diary_idx") Long idx, @RequestParam(name = "contents") String contents) {
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        Long result = diaryService.saveComment(idx, contents);

        return new ResponseData(constValues.DONE, "댓글이 저장되었습니다.", result);
    }
}
