package com.lovediary.dto;

import com.lovediary.entity.Emotion;
import lombok.*;

/**
 *
 * EmotionDto
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-19
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-19          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmotionDto {
    private Long idx;
    private Long imageIdx;

    @Builder
    public EmotionDto(Long idx, Long imageIdx) {
        this.idx = idx;
        this.imageIdx = imageIdx;
    }
}
