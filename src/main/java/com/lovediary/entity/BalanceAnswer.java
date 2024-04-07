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
 * BalanceAnswer
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BalanceAnswer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long balanceIdx;

    @Column
    private Long balanceItemIdx;

    @Column
    private Long accountIdx;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Builder
    public BalanceAnswer(Long idx, Long balanceIdx, Long balanceItemIdx, Long accountIdx, Timestamp registDate) {
        this.idx = idx;
        this.balanceIdx = balanceIdx;
        this.balanceItemIdx = balanceItemIdx;
        this.accountIdx = accountIdx;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
    }
}
