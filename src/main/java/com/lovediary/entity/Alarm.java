package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 
 * Alarm
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-26
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-26          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends TimeEntity {
    @Id
    private Long idx;

    @Column(length = 300)
    private String contents;

    @Column(length = 300)
    private String link;

    @Column
    private char readYn;

    @Column
    private Timestamp readDate;

    @Builder
    public Alarm(Long idx, String contents, String link, char readYn, Timestamp readDate) {
        this.idx = idx;
        this.contents = contents;
        this.link = link;
        this.readYn = readYn;
        this.readDate = readDate;
    }
}
