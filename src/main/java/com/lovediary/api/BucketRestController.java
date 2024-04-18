package com.lovediary.api;

import com.lovediary.dto.BucketDto;
import com.lovediary.dto.BucketItemDto;
import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.service.BucketService;
import com.lovediary.util.Session;
import com.lovediary.values.ResponseData;
import com.lovediary.values.SessionData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class BucketRestController extends Session {
    private BucketService bucketService;
    public BucketRestController(BucketService service) {
        this.bucketService = service;
    }

    //버킷리스트 저장
    @PostMapping("/bucket/save")
    public ResponseData bucketSave(HttpServletRequest request, BucketDto bucketDto){
        if(bucketDto.getTitle() == null || bucketDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(bucketDto.getContents() == null || bucketDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        bucketDto.setAccountIdx(this.getLoginData(request).getAccountIdx());

        bucketService.saveItem(bucketDto);

        return new ResponseData(constValues.DONE, "버킷리스트가 저장되었습니다.", null);
    }

    //항목 저장
    @PostMapping("/bucket/item/save")
    public ResponseData bucketItemSave(HttpServletRequest request,
                                       @RequestParam(name = "date", required = false) String achieveDate,
                                       BucketItemDto bucketItemDto) throws ParseException {
        if(bucketItemDto.getTitle() == null || bucketItemDto.getTitle().isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }

        if(bucketItemDto.getContents() == null || bucketItemDto.getContents().isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        Timestamp timestamp = null;
        if(achieveDate != null && !achieveDate.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            Date date = formatter.parse(achieveDate);
            timestamp = new Timestamp(date.getTime());
        }

        bucketItemDto.setAccountIdx(this.getLoginData(request).getAccountIdx());
        bucketItemDto.setAchieveDate(timestamp);

        bucketService.saveBucketItem(bucketItemDto);

        return new ResponseData(constValues.DONE, "버킷리스트의 항목이 저장되었습니다.", null);
    }

    // 타임캡슐 삭제
    @PostMapping("/bucket/delete")
    public ResponseData deleteBucket(HttpServletRequest request,
                                     @RequestParam(name = "idx") Long idx) {
        if(idx == null) {
            return new ResponseData(constValues.ERROR, "삭제할 버킷리스트가 없습니다.", null);
        }

        BucketDto bucketDto = bucketService.getOne(idx);
        bucketDto.setDeleteYn('Y');
        bucketDto.setDeleteDate(new Timestamp(System.currentTimeMillis()));

        Long result = bucketService.saveItem(bucketDto);

        return new ResponseData(constValues.DONE, "버킷리스트가 삭제되었습니다.", result);
    }
}
