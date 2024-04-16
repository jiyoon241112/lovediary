package com.lovediary.dto;

import com.lovediary.entity.Puppy;
import lombok.*;

/**
 *
 * PuppyDto
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PuppyDto {
    private Long idx;
    private Long coupleIdx;
    private String name;
    private Integer condition;
    private Integer hungry;
    private Long totalWalkDistance;

    public Puppy toEntity() {
        return Puppy.builder()
                .idx(idx)
                .coupleIdx(coupleIdx)
                .name(name)
                .condition(condition)
                .hungry(hungry)
                .totalWalkDistance(totalWalkDistance)
                .build();
    }

    @Builder
    public PuppyDto(Long idx, Long coupleIdx, String name, Integer condition, Integer hungry, Long totalWalkDistance) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.name = name;
        this.condition = condition;
        this.hungry = hungry;
        this.totalWalkDistance = totalWalkDistance;
    }
}
