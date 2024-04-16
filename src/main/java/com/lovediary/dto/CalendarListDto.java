package com.lovediary.dto;

import lombok.*;

/**
 *
 * CalendarDto
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-16          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CalendarListDto {
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public CalendarListDto(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
