package com.lovediary.dto;
/**
 * 
 * BookMarkThemeDto
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
public class BookMarkThemeDto {
    private Long idx;
    private Long imageIdx;
    private String name;
    private Long themeIdx;

    @Builder
    public BookMarkThemeDto(Long idx, Long imageIdx, String name, Long themeIdx) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.name = name;
        this.themeIdx = themeIdx;
    }
}
