package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * Couple
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-30
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-30          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100)
    private String name;

    @Column
    private Date startDate;

    @Column(length = 4)
    private String questionTime;

    @Column
    private Long point;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @LastModifiedDate
    private Timestamp modifyDate;

    @Builder
    public Couple(Long idx, String name, Date startDate, String questionTime, Long point, Character deleteYn, Timestamp registDate, Timestamp modifyDate) {
        this.idx = idx;
        this.name = name;
        this.startDate = startDate;
        this.questionTime = questionTime;
        this.point = point;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
    }
}
