package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * CoupleQuestion
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-01
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-01          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoupleQuestion {
    @Id
    private Long idx;

    @Column(length = 300)
    private String contents;

    @Builder
    public CoupleQuestion(Long idx, String contents) {
        this.idx = idx;
        this.contents = contents;
    }
}
