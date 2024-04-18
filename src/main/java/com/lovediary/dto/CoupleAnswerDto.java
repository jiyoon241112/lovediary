package com.lovediary.dto;

import com.lovediary.entity.CoupleAnswer;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * CoupleAnswerDto
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
@Setter
@ToString
@NoArgsConstructor
public class CoupleAnswerDto {
    private Long idx;
    private Long questionIdx;
    private String questionContents;
    private Character mansAnswerYn;

    private Long mansAccountIdx;
    private String mansAccountName;
    private Long mansProfileIdx;
    private Long mansEmotionIdx;
    private String mansAnswerContents;
    private Timestamp mansAnswerDate;

    private Long womansAccountIdx;
    private String womansAccountName;
    private Long womansProfileIdx;
    private Character womansAnswerYn;
    private Long womansEmotionIdx;
    private String womansAnswerContents;
    private Timestamp womansAnswerDate;
    private Long coupleIdx;
    private Date questionDate;

    public CoupleAnswer toEntity() {
        return CoupleAnswer.builder()
                .idx(idx)
                .questionIdx(questionIdx)
                .questionContents(questionContents)
                .mansAnswerYn(mansAnswerYn)
                .mansEmotionIdx(mansEmotionIdx)
                .mansAnswerContents(mansAnswerContents)
                .mansAnswerDate(mansAnswerDate)
                .womansAnswerYn(womansAnswerYn)
                .womansEmotionIdx(womansEmotionIdx)
                .womansAnswerContents(womansAnswerContents)
                .womansAnswerDate(womansAnswerDate)
                .coupleIdx(coupleIdx)
                .questionDate(questionDate)
                .build();
    }

    @Builder
    public CoupleAnswerDto(Long idx, Long questionIdx, String questionContents, Character mansAnswerYn, Long mansEmotionIdx, String mansAnswerContents, Timestamp mansAnswerDate, Character womansAnswerYn, Long womansEmotionIdx, String womansAnswerContents, Timestamp womansAnswerDate, Long coupleIdx, Date questionDate) {
        this.idx = idx;
        this.questionIdx = questionIdx;
        this.questionContents = questionContents;
        this.mansAnswerYn = mansAnswerYn;
        this.mansEmotionIdx = mansEmotionIdx;
        this.mansAnswerContents = mansAnswerContents;
        this.mansAnswerDate = mansAnswerDate;
        this.womansAnswerYn = womansAnswerYn;
        this.womansEmotionIdx = womansEmotionIdx;
        this.womansAnswerContents = womansAnswerContents;
        this.womansAnswerDate = womansAnswerDate;
        this.coupleIdx = coupleIdx;
        this.questionDate = questionDate;
    }
}
