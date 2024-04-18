package com.lovediary.api;

import com.lovediary.dto.CommunityDto;
import com.lovediary.dto.CommunityReplyDto;
import com.lovediary.service.CommunityService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

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
    public ResponseData save(HttpServletRequest request,
                             @RequestParam(name = "idx", required = false) Long idx,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "contents") String contents) {
        if(title == null || title.isEmpty()) {
            return new ResponseData(constValues.ERROR, "제목을 입력해주세요.", null);
        }
        
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        CommunityDto community = null;
        if(idx != null) {
            community = communityService.getOne(idx);
            community.setTitle(title);
            community.setContents(contents);
            community.setModifyDate(new Timestamp(System.currentTimeMillis()));
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

    // 삭제
    @PostMapping("/community/remove")
    public ResponseData remove(HttpServletRequest request,
                               @RequestParam(name = "idx", required = false) Long idx) {
        CommunityDto community = communityService.getOne(idx);
        community.setDeleteYn('Y');
        community.setDeleteDate(new Timestamp(System.currentTimeMillis()));

        communityService.saveItem(community);

        return new ResponseData(constValues.DONE, "삭제되었습니다.", null);
    }

    // 댓글 저장
    @PostMapping("/community/save_comment")
    public ResponseData saveComment(HttpServletRequest request,
                                    @RequestParam(name = "idx", required = false) Long idx,
                                    @RequestParam(name = "community_idx", required = false) Long communityIdx,
                                    @RequestParam(name = "reply_idx", required = false) Long replyIdx,
                                    @RequestParam(name = "contents", required = false) String contents) {
        if(contents == null || contents.isEmpty()) {
            return new ResponseData(constValues.ERROR, "내용을 입력해주세요.", null);
        }

        if(replyIdx != null && replyIdx < 1) {
            replyIdx = null;
        }

        CommunityReplyDto replyDto = null;
        if(idx == null) {
            replyDto = CommunityReplyDto.builder()
                    .communityIdx(communityIdx)
                    .replyIdx(replyIdx)
                    .contents(contents)
                    .accountIdx(1L)
                    .build();
        } else {
            replyDto = communityService.getCommentOne(idx);
            replyDto.setContents(contents);
            replyDto.setModifyDate(new Timestamp(System.currentTimeMillis()));
        }

        Long result = communityService.saveComment(replyDto);

        return new ResponseData(constValues.DONE, "댓글이 저장되었습니다.", result);
    }

    // 댓글 저장
    @PostMapping("/community/remove_comment")
    public ResponseData removeComment(@RequestParam(name = "idx") Long idx) {
        CommunityReplyDto replyDto = communityService.getCommentOne(idx);
        replyDto.setDeleteYn('Y');
        replyDto.setDeleteDate(new Timestamp(System.currentTimeMillis()));

        Long result = communityService.saveComment(replyDto);

        return new ResponseData(constValues.DONE, "댓글이 삭제되었습니다.", result);
    }
}
