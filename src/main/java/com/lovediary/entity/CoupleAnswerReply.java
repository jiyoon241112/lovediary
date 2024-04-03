package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class CoupleAnswerReply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long coupleAnswerIdx;

    @Column(length = 300)
    private String contents;

    @Column
    private Long accountIdx;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp modifyDate;

    @Builder
    public CoupleAnswerReply(Long idx, Long coupleAnswerIdx, String contents, Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate) {
        this.idx = idx;
        this.coupleAnswerIdx = coupleAnswerIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
    }
}
