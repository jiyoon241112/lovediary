package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.BalanceReply;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * BalanceReplyDto
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BalanceReplyDto {
    private Long idx;
    private Long balanceIdx;
    private Long replyIdx;
    private String contents;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public BalanceReply toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return BalanceReply.builder()
                .idx(idx)
                .balanceIdx(balanceIdx)
                .replyIdx(replyIdx)
                .contents(contents)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .account(account)
                .build();
    }

    @Builder
    public BalanceReplyDto(Long idx, Long balanceIdx, Long replyIdx, String contents, Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.balanceIdx = balanceIdx;
        this.replyIdx = replyIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
