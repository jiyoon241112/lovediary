package com.lovediary.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

/**
 *
 * CourseDto
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
public class CourseDto {
    private Long idx;
    private Long themeIdx;
    private String title;
    private Long accountIdx;
    private Character openYn;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    @Builder
    public CourseDto(Long idx, Long themeIdx, String title, Long accountIdx, Character openYn, Character deleteYn, Timestamp registDate,
                  Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.title = title;
        this.accountIdx = accountIdx;
        this.openYn = openYn;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
