package com.lovediary.api;

import com.lovediary.dto.CoupleAnswerDto;
import com.lovediary.service.AccountService;
import com.lovediary.service.QuestionService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.regex.Pattern;

/**
 * 
 * QuestionRestController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
@RestController
public class QuestionRestController {
    private final QuestionService questionService;
    private final AccountService accountService;
    public QuestionRestController(QuestionService questionService, AccountService accountService) {
        this.questionService = questionService;
        this.accountService = accountService;
    }

    // 저장
    @PostMapping("/question/save")
    public ResponseData save(HttpServletRequest request, @RequestParam(name = "idx") Long idx, @RequestParam(name = "contents") String contents, @RequestParam(name = "emotion_idx") Long emotionIdx) {
        String onlyWhitespace = "^\\s+$";

        if(idx == null) {
            return new ResponseData(constValues.ERROR, "잘못된 요청입니다.", null);
        }

        if(contents == null || contents.isEmpty() || Pattern.matches(onlyWhitespace, contents)) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        if(emotionIdx == null) {
            return new ResponseData(constValues.ERROR, "오늘의 기분을 선택해주세요.", null);
        }

        CoupleAnswerDto answer = questionService.getOne(idx);
        if(accountService.getOne(2L).getGender() == 'M') {
            answer.setMansAnswerYn('Y');
            answer.setMansAnswerContents(contents);
            answer.setMansEmotionIdx(emotionIdx);
            answer.setMansAnswerDate(new Timestamp(System.currentTimeMillis()));
        } else {
            answer.setWomansAnswerYn('Y');
            answer.setWomansAnswerContents(contents);
            answer.setWomansEmotionIdx(emotionIdx);
            answer.setWomansAnswerDate(new Timestamp(System.currentTimeMillis()));
        }

        Long result = questionService.saveItem(answer);

        return new ResponseData(constValues.DONE, "저장되었습니다.", result);
    }
}
