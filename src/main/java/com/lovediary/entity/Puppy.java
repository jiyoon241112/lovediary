package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * Puppy
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Puppy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long coupleIdx;

    @Column(length = 100)
    private String name;

    @Column
    private Integer condition;

    @Column
    private Integer hungry;

    @Column
    private Long totalWalkDistance;

    @Builder
    public Puppy(Long idx, Long coupleIdx, String name, Integer condition, Integer hungry, Long totalWalkDistance) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.name = name;
        this.condition = condition;
        this.hungry = hungry;
        this.totalWalkDistance = totalWalkDistance;
    }
}
