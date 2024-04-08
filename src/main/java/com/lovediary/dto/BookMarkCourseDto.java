package com.lovediary.dto;
/**
 *
 * BookMarkCourseDto
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookMarkCourseDto {
    private Long idx;
    private String title;
    private String address;
    private Long datePlaceIdx;
    private Integer placeCnt;
    private Long courseIdx;

    @Builder
    public BookMarkCourseDto(Long idx, String title, String address, Long datePlaceIdx, Integer placeCnt, Long courseIdx) {
        this.idx = idx;
        this.title = title;
        this.address = address;
        this.datePlaceIdx = datePlaceIdx;
        this.placeCnt = placeCnt;
        this.courseIdx = courseIdx;
    }
}
