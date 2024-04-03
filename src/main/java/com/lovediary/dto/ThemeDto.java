package com.lovediary.dto;

import com.lovediary.entity.Theme;
import lombok.*;

/**
 *
 * ThemeDto
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
public class ThemeDto {
    private Long idx;
    private Long imageIdx;
    private String name;

    public Theme toEntity() {
        return Theme.builder()
                .idx(idx)
                .imageIdx(imageIdx)
                .name(name)
                .build();
    }

    @Builder
    public ThemeDto(Long idx, Long imageIdx, String name) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.name = name;
    }
}
