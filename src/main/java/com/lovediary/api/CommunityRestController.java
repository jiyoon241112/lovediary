package com.lovediary.api;

import com.lovediary.dto.CommunityDto;
import com.lovediary.service.CommunityService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * CommunityRestController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@RestController
public class CommunityRestController {
    private final CommunityService communityService;
    public CommunityRestController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 저장
    @PostMapping("/community/save")
    public ResponseData save(HttpServletRequest request, @RequestParam(name = "idx", required = false) Long idx, @RequestParam(name = "title") String title, @RequestParam(name = "contents") String contents) {
        if(title == null || title.isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }
        
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        CommunityDto community = null;
        if(idx != null) {
            community = communityService.getOne(idx);
        } else {
            community = CommunityDto.builder()
                    .title(title)
                    .contents(contents)
                    .accountIdx(1L)
                    .build();
        }

        Long result = communityService.saveItem(community);

        return new ResponseData(constValues.DONE, "저장되었습니다.", result);
    }

    // 댓글 저장
    @PostMapping("/community/save_comment")
    public ResponseData saveComment(HttpServletRequest request, @RequestParam(name = "idx") Long idx, @RequestParam(name = "reply_idx", required = false) Long replyIdx, @RequestParam(name = "contents") String contents) {
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        if(replyIdx < 1) {
            replyIdx = null;
        }

        Long result = communityService.saveComment(idx, replyIdx, contents);

        return new ResponseData(constValues.DONE, "댓글이 저장되었습니다.", result);
    }
}
