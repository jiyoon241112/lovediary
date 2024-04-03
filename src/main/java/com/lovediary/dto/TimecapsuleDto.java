package com.lovediary.dto;

import com.lovediary.entity.Timecapsule;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * TimeCapsuleDto
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
public class TimecapsuleDto {
    private Long idx;
    private Date openDate;
    private String title;
    private String contents;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public Timecapsule toEntity() {
        return Timecapsule.builder()
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
    public TimecapsuleDto(Long idx, Date openDate, String title, String contents, Long accountIdx, Character deleteYn,
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
