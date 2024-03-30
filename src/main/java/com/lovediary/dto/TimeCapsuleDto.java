package com.lovediary.dto;

import com.lovediary.entity.TimeCapsule;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TimeCapsuleDto {
    private Long idx;
    private Date openDate;
    private String title;
    private String contents;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public TimeCapsule toEntity() {
        return TimeCapsule.builder()
                .idx(idx)
                .openDate(openDate)
                .title(title)
                .contents(contents)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public TimeCapsuleDto(Long idx, Date openDate, String title, String contents, Long accountIdx,Character deleteYn,
                          Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.openDate = openDate;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
