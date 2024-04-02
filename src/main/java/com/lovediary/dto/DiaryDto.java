package com.lovediary.dto;

import com.lovediary.entity.Diary;
import lombok.*;

import java.sql.Timestamp;

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
    private Timestamp registDate;

    public Diary toEntity() {
        return Diary.builder()
                .idx(idx)
                .coupleIdx(coupleIdx)
                .emotionIdx(emotionIdx)
                .title(title)
                .contents(contents)
                .accountIdx(accountIdx)
                .registDate(registDate)
                .build();
    }

    @Builder
    public DiaryDto(Long idx, Long coupleIdx, Long emotionIdx, String title, String contents,
                    Long accountIdx,Timestamp registDate) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.emotionIdx = emotionIdx;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.registDate = registDate;
    }
}
