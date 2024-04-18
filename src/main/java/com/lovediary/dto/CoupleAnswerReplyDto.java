package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.CoupleAnswerReply;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * CoupleAnswerReplyDto
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
public class CoupleAnswerReplyDto {
    private Long idx;
    private Long coupleAnswerIdx;
    private String contents;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;

    public CoupleAnswerReply toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return CoupleAnswerReply.builder()
                .idx(idx)
                .coupleAnswerIdx(coupleAnswerIdx)
                .contents(contents)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .account(account)
                .build();
    }

    @Builder
    public CoupleAnswerReplyDto(Long idx, Long coupleAnswerIdx, String contents, Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate) {
        this.idx = idx;
        this.coupleAnswerIdx = coupleAnswerIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
    }
}
