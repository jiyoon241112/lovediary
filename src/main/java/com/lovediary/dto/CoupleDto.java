package com.lovediary.dto;

import com.lovediary.entity.Couple;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * CoupleDto
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-30
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-30          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CoupleDto {
    private Long idx;
    private String name;
    private Date startDate;
    private String questionTime;
    private Long point;
    private String code;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;

    public Couple toEntity() {
        return Couple.builder()
                .idx(idx)
                .name(name)
                .startDate(startDate)
                .questionTime(questionTime)
                .point(point)
                .code(code)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .build();
    }

    @Builder
    public CoupleDto(Long idx, String name, Date startDate, String questionTime, Long point, String code, Character deleteYn, Timestamp registDate, Timestamp modifyDate) {
        this.idx = idx;
        this.name = name;
        this.startDate = startDate;
        this.questionTime = questionTime;
        this.point = point;
        this.code = code;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
    }
}
