package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.Diary;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * DiaryDto
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
public class DiaryDto {
    private Long idx;
    private Long coupleIdx;
    private Long emotionIdx;
    private String title;
    private String contents;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Timestamp registDate;

    public Diary toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return Diary.builder()
                .idx(idx)
                .coupleIdx(coupleIdx)
                .emotionIdx(emotionIdx)
                .title(title)
                .contents(contents)
                .accountIdx(accountIdx)
                .registDate(registDate)
                .account(account)
                .build();
    }

    @Builder
    public DiaryDto(Long idx, Long coupleIdx, Long emotionIdx, String title, String contents,
                    Long accountIdx, String accountName ,Long profileIdx, Timestamp registDate) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.emotionIdx = emotionIdx;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.registDate = registDate;
    }
}
