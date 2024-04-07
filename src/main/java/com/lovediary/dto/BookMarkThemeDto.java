package com.lovediary.dto;

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
