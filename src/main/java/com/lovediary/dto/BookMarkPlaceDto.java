package com.lovediary.dto;
/**
 * 
 * BookMarkPlaceDto
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookMarkPlaceDto {
    private Long idx;
    private Long themeIdx;
    private Character type;
    private String title;
    private String address;
    private Long placeIdx;

    @Builder
    public BookMarkPlaceDto(Long idx, Long themeIdx, Character type, String title, String address, Long placeIdx) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.type = type;
        this.title = title;
        this.address = address;
        this.placeIdx = placeIdx;
    }
}
