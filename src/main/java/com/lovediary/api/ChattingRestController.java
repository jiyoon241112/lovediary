package com.lovediary.api;

import com.lovediary.dto.ChattingDto;
import com.lovediary.service.ChattingService;
import com.lovediary.service.FileService;
import com.lovediary.util.Session;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * ChattingRestController
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
@RestController
public class ChattingRestController extends Session {
    private final ChattingService chattingService;
    private final FileService fileService;
    public ChattingRestController(ChattingService chattingService, FileService fileService) {
        this.chattingService = chattingService;
        this.fileService = fileService;
    }

    // 채팅 기록 조회
    @GetMapping("/chatting/get")
    public ResponseData getChatting(HttpServletRequest request,
                                    @RequestParam(name = "date", required = false) String date) {
        List<ChattingDto> result = chattingService.getList(date, this.getLoginData(request));

        return new ResponseData(constValues.DONE, "조회했습니다.", result);
    }

    // 채팅 저장
    @PostMapping("/chatting/save")
    public ResponseData saveChatting(HttpServletRequest request,
                                     @RequestParam(name = "contents", required = false) String contents) {
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "채팅 내용을 입력해주세요.", null);
        }

        ChattingDto chattingDto = ChattingDto.builder()
                .contents(contents)
                .accountIdx(this.getLoginData(request).getAccountIdx())
                .build();

        Long result = chattingService.saveItem(chattingDto);

        return new ResponseData(constValues.DONE, "저장했습니다.", result);
    }

    // 채팅 이미지 업로드
    @PostMapping("/chatting/upload")
    public ResponseData saveUploadFile(HttpServletRequest request,
                                       @RequestParam(name = "file", required = false) MultipartFile uploadFile) throws IOException {
        if(uploadFile == null) {
            return new ResponseData(constValues.ERROR, "파일을 업로드해주세요.", null);
        }

        Long uploadIdx = fileService.saveItem(uploadFile, 3);
        ChattingDto chattingDto = ChattingDto.builder()
                .imageIdx(uploadIdx)
                .accountIdx(this.getLoginData(request).getAccountIdx())
                .build();

        Long idx = chattingService.saveItem(chattingDto);

        Map<String, Long> result = new HashMap<>();
        result.put("idx", idx);
        result.put("image_idx", uploadIdx);

        return new ResponseData(constValues.DONE, "저장했습니다.", result);
    }

    // 최근 채팅 날짜 조회
    @GetMapping("/get/date")
    public ResponseData getDate(HttpServletRequest request,
                                @RequestParam(name = "date", required = false) String date) {
        String result = chattingService.getLastDate(date, this.getLoginData(request));

        return new ResponseData(constValues.DONE, "조회했습니다.", result);
    }
}
