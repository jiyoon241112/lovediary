package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.Bucket;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * BucketDto
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BucketDto {
    private Long idx;
    private Long thumbnailIdx;
    private String title;
    private String contents;
    private Character achieveYn;
    private Timestamp achieveDate;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public Bucket toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return Bucket.builder()
                .idx(idx)
                .thumbnailIdx(thumbnailIdx)
                .title(title)
                .contents(contents)
                .achieveYn(achieveYn)
                .achieveDate(achieveDate)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .account(account)
                .build();
    }

    @Builder
    public BucketDto(Long idx, Long thumbnailIdx, String title, String contents, Character achieveYn, Timestamp achieveDate,
                     Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate,
                     Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.thumbnailIdx = thumbnailIdx;
        this.title = title;
        this.contents = contents;
        this.achieveYn = achieveYn;
        this.achieveDate = achieveDate;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
