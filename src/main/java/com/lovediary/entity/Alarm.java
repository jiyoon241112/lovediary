package com.lovediary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
public class Alarm {
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

    @Column
    private Long accountIdx;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul")
    private Timestamp registDate;

    @Builder
    public Alarm(Long idx, String contents, String link, char readYn, Timestamp readDate, Long accountIdx, Timestamp registDate) {
        this.idx = idx;
        this.contents = contents;
        this.link = link;
        this.readYn = readYn;
        this.readDate = readDate;
        this.accountIdx = accountIdx;
        this.registDate = registDate;
    }
}
