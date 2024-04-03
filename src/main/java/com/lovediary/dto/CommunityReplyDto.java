package com.lovediary.dto;

import com.lovediary.entity.CommunityReply;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * CommunityReplyDto
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommunityReplyDto {
    private Long idx;
    private Long communityIdx;
    private Long replyIdx;
    private String contents;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public CommunityReply toEntity() {
        return CommunityReply.builder()
                .idx(idx)
                .communityIdx(communityIdx)
                .replyIdx(replyIdx)
                .contents(contents)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public CommunityReplyDto(Long idx, Long communityIdx, Long replyIdx, String contents, Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.communityIdx = communityIdx;
        this.replyIdx = replyIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
