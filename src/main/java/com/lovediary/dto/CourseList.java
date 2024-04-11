package com.lovediary.dto;
/**
 *
 * CourseList
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/
public interface CourseList {
    Long getIdx();
    Long getThemeIdx();
    String getCourseTitle();
    String getTitle();
    String getContent();
    String getHomepage();
}
