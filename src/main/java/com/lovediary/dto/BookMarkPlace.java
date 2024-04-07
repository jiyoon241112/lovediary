package com.lovediary.dto;
/**
 *
 * BookMarkPlace
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          JJY             최초 등록
 **/
public interface BookMarkPlace {
    Long getIdx();
    Long getThemeIdx();
    Character getType();
    String getTitle();
    String getAddress();
    Long getPlaceIdx();
}
