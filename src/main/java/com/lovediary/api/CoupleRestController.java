package com.lovediary.api;

import com.lovediary.dto.CoupleDto;
import com.lovediary.entity.Couple;
import com.lovediary.service.CoupleService;
import com.lovediary.util.Session;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * CoupleRestController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-17
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-17          HTH             최초 등록
 **/
@RestController
public class CoupleRestController extends Session {
    private final CoupleService coupleService;
    public CoupleRestController(CoupleService coupleService) {
        this.coupleService = coupleService;
    }

    // 커플 생성
    @PostMapping("/couple/regist")
    public ResponseData registCouple(HttpServletRequest request,
                                     @RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return new ResponseData(constValues.ERROR, "커플명을 입력해주세요.", null);
        }

        CoupleDto coupleDto = CoupleDto.builder()
                .name(name)
                .point(10L)
                .code(coupleService.getCode())
                .build();

        Long result = coupleService.saveItem(coupleDto);
        
        // 커플 고유번호를 세션에 저장
        HttpSession session = this.getSessionData(request);
        session.setAttribute(constValues.COUPLE_DATA, result);

        return new ResponseData(constValues.DONE, "저장했습니다.", result);
    }

    // 코드 입력
    @PostMapping("/couple/code")
    public ResponseData registCode(HttpServletRequest request, @RequestParam(name = "code", required = false) String code) {
        if (code == null || code.isEmpty()) {
            return new ResponseData(constValues.ERROR, "코드를 입력해주세요.", null);
        }

        Long result = coupleService.checkCode(code);

        // 커플 고유번호를 세션에 저장
        HttpSession session = this.getSessionData(request);
        session.setAttribute(constValues.COUPLE_DATA, result);

        return new ResponseData(constValues.DONE, "저장했습니다.", null);
    }

    // 코드 입력 확인
    @PostMapping("/couple/check")
    public ResponseData checkCode(HttpServletRequest request) {
        HttpSession session = this.getSessionData(request);
        Long idx = (Long) session.getAttribute(constValues.COUPLE_DATA);

        String code = coupleService.getOne(idx).getCode();

        if (code == null || code.isEmpty()) {
            return new ResponseData(constValues.DONE, "가입했습니다.", null);
        } else {
            return new ResponseData(constValues.ERROR, "아직 가입하지 않았습니다.", null);
        }
    }
}
