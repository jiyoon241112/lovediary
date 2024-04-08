package com.lovediary.dto;
/**
 * 
 * BookMarkCourse
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/
public interface BookMarkCourse {
    Long getIdx();
    String getTitle();
    String getAddress();
    Long getDatePlaceIdx();
    Integer getPlaceCnt();
    Long getCourseIdx();
}
