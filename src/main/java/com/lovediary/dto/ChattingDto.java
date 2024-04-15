package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.Chatting;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * ChattingDto
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-15
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-15          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChattingDto {
    private Long idx;
    private Long emoticonIdx;
    private Long imageIdx;
    private String contents;
    private Character readYn;
    private Timestamp readDate;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp deleteDate;

    public Chatting toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return Chatting.builder()
                .idx(idx)
                .emoticonIdx(emoticonIdx)
                .imageIdx(imageIdx)
                .contents(contents)
                .readYn(readYn)
                .readDate(readDate)
                .accountIdx(accountIdx)
                .registDate(registDate)
                .deleteDate(deleteDate)
                .account(account)
                .build();
    }

    @Builder
    public ChattingDto(Long idx, Long emoticonIdx, Long imageIdx, String contents, Character readYn, Timestamp readDate, Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate, Timestamp deleteDate) {
        this.idx = idx;
        this.emoticonIdx = emoticonIdx;
        this.imageIdx = imageIdx;
        this.contents = contents;
        this.readYn = readYn;
        this.readDate = readDate;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.deleteDate = deleteDate;
    }
}
