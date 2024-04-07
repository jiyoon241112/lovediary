package com.lovediary.dto;

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
