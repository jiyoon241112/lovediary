package com.lovediary.dto;

import com.lovediary.entity.Alarm;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 
 * AlarmDto
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
public class AlarmDto {
    private Long idx;
    private String contents;
    private String link;
    private char readYn;
    private Timestamp readDate;
    private Long accountIdx;
    private Timestamp registDate;

    public Alarm toEntity() {
        return Alarm.builder()
                .idx(idx)
                .contents(contents)
                .link(link)
                .readYn(readYn)
                .readDate(readDate)
                .build();
    }

    @Builder
    public AlarmDto(Long idx, String contents, String link, char readYn, Timestamp readDate, Long accountIdx, Timestamp registDate) {
        this.idx = idx;
        this.contents = contents;
        this.link = link;
        this.readYn = readYn;
        this.readDate = readDate;
        this.accountIdx = accountIdx;
        this.registDate = registDate;
    }
}
