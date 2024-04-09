package com.lovediary.api;

import com.lovediary.dto.BucketDto;
import com.lovediary.service.BucketService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * BucketRestController
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/
@RestController
public class BucketRestController {
    private BucketService bucketService;
    public BucketRestController(BucketService service) {
        this.bucketService = service;
    }

    @PostMapping("/bucket/save")
    public ResponseData bucketSave(HttpServletRequest request, BucketDto bucketDto){
        HttpSession session = request.getSession(true);
        session.getAttribute(constValues.LOGIN_USER);

        if(bucketDto.getTitle() == null || bucketDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(bucketDto.getContents() == null || bucketDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        bucketDto.setAccountIdx(2L);

        bucketService.saveItem(bucketDto);

        return new ResponseData(constValues.DONE, "타임캡슐이 저장되었습니다.", null);
    }
}
