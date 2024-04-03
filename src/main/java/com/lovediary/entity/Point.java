package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

/**
 * 
 * Point
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Point {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long coupleIdx;

    @Column(length = 300)
    private String contents;

    @Column
    private Long point;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Builder
    public Point(Long idx, Long coupleIdx, String contents, Long point, Timestamp registDate) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.contents = contents;
        this.point = point;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
    }
}
