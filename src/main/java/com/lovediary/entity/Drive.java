package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * Drive
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-10
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-10          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Drive {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long coupleIdx;

    @Column(length = 100)
    private String path;

    @Column
    private Long useSize;

    @Column
    private Long totalSize;

    @Builder
    public Drive(Long idx, Long coupleIdx, String path, Long useSize, Long totalSize) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.path = path;
        this.useSize = useSize;
        this.totalSize = totalSize;
    }
}
