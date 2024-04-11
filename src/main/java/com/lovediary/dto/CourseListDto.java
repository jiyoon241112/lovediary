package com.lovediary.dto;

import lombok.*;

/**
 * 
 * CourseListDto
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CourseListDto {
    private Long idx;
    private Long themeIdx;
    private String courseTitle;
    private String title;
    private String content;
    private String homepage;

    @Builder
    public CourseListDto(Long idx, Long themeIdx, String courseTitle, String title, String content, String homepage) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.courseTitle = courseTitle;
        this.title = title;
        this.content = content;
        this.homepage = homepage;
    }
}
