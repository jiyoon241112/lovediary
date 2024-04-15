package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.Balance;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * BalanceDto
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
public class BalanceDto {
    private Long idx;
    private String title;
    private String contents;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public Balance toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return Balance.builder()
                .idx(idx)
                .title(title)
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
    public BalanceDto(Long idx, String title, String contents, Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.title = title;
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
