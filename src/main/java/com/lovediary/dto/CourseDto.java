package com.lovediary.dto;

import lombok.*;

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
    private Long imageIdx;
    private String name;

    @Builder
    public CourseDto(Long idx, Long imageIdx, String name) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.name = name;
    }
}
