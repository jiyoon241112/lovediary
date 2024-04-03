package com.lovediary.dto;

import com.lovediary.entity.ThemeBookMark;
import jakarta.persistence.Column;
import lombok.*;

/**
 *
 * ThemeBookMarkDto
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
public class ThemeBookMarkDto {
    private Long idx;
    private Long themeIdx;
    private Long accountIdx;

    public ThemeBookMark toEntity() {
        return ThemeBookMark.builder()
                .idx(idx)
                .themeIdx(themeIdx)
                .accountIdx(accountIdx)
                .build();
    }

    @Builder
    public ThemeBookMarkDto(Long idx, Long themeIdx, Long accountIdx) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.accountIdx = accountIdx;
    }
}
