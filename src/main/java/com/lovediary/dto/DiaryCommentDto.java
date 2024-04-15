package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.DiaryComment;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * DiaryCommentDto
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryCommentDto {
    private Long idx;
    private Long coupleDiaryIdx;
    private String contents;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public DiaryComment toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return DiaryComment.builder()
                .idx(idx)
                .coupleDiaryIdx(coupleDiaryIdx)
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
    public DiaryCommentDto(Long idx, Long coupleDiaryIdx, String contents, Long accountIdx, String accountName, Long profileIdx,
                           Character deleteYn,Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.coupleDiaryIdx = coupleDiaryIdx;
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
