package com.lovediary.dto;

import com.lovediary.entity.DiaryComment;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DiaryCommentDto {
    private Long idx;
    private Long coupleDiaryIdx;
    private String contents;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public DiaryComment toEntity() {
        return DiaryComment.builder()
                .idx(idx)
                .coupleDiaryIdx(coupleDiaryIdx)
                .contents(contents)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public DiaryCommentDto(Long idx, Long coupleDiaryIdx, String contents, Long accountIdx,Character deleteYn,
                           Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.coupleDiaryIdx = coupleDiaryIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
