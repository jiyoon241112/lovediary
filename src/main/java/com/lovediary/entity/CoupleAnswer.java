package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * CoupleAnswer
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoupleAnswer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long questionIdx;

    @Column(length = 300)
    private String questionContents;

    @Column
    private Character mansAnswerYn;

    @Column
    private Long mansEmotionIdx;

    @Column(length = 300)
    private String mansAnswerContents;

    @Column
    private Timestamp mansAnswerDate;

    @Column
    private Character womansAnswerYn;

    @Column
    private Long womansEmotionIdx;

    @Column(length = 300)
    private String womansAnswerContents;

    @Column
    private Timestamp womansAnswerDate;

    @Column
    private Long coupleIdx;

    @Column
    private Date questionDate;

    @Builder
    public CoupleAnswer(Long idx, Long questionIdx, String questionContents, Character mansAnswerYn, Long mansEmotionIdx, String mansAnswerContents, Timestamp mansAnswerDate, Character womansAnswerYn, Long womansEmotionIdx, String womansAnswerContents, Timestamp womansAnswerDate, Long coupleIdx, Date questionDate) {
        this.idx = idx;
        this.questionIdx = questionIdx;
        this.questionContents = questionContents;
        this.mansAnswerYn = mansAnswerYn == null ? 'N' : mansAnswerYn;
        this.mansEmotionIdx = mansEmotionIdx;
        this.mansAnswerContents = mansAnswerContents;
        this.mansAnswerDate = mansAnswerDate;
        this.womansAnswerYn = womansAnswerYn == null ? 'N' : womansAnswerYn;
        this.womansEmotionIdx = womansEmotionIdx;
        this.womansAnswerContents = womansAnswerContents;
        this.womansAnswerDate = womansAnswerDate;
        this.coupleIdx = coupleIdx;
        this.questionDate = questionDate;
    }
}
