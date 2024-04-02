package com.lovediary.dto;

import com.lovediary.entity.CoupleQuestion;
import lombok.*;

/**
 * 
 * CoupleQuestionDto
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-01
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-01          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CoupleQuestionDto {
    private Long idx;
    private String contents;

    public CoupleQuestion toEntity() {
        return CoupleQuestion.builder()
                .idx(idx)
                .contents(contents)
                .build();
    }

    @Builder
    public CoupleQuestionDto(Long idx, String contents) {
        this.idx = idx;
        this.contents = contents;
    }
}
