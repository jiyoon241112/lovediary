package com.lovediary.dto;

import com.lovediary.entity.BalanceAnswer;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * BalanceAnswerDto
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
@Setter
@ToString
@NoArgsConstructor
public class BalanceAnswerDto {
    private Long idx;
    private Long balanceIdx;
    private Long balanceItemIdx;
    private Long accountIdx;
    private Timestamp registDate;

    public BalanceAnswer toEntity() {
        return BalanceAnswer.builder()
                .idx(idx)
                .balanceIdx(balanceIdx)
                .balanceItemIdx(balanceItemIdx)
                .accountIdx(accountIdx)
                .registDate(registDate)
                .build();
    }

    @Builder
    public BalanceAnswerDto(Long idx, Long balanceIdx, Long balanceItemIdx, Long accountIdx, Timestamp registDate) {
        this.idx = idx;
        this.balanceIdx = balanceIdx;
        this.balanceItemIdx = balanceItemIdx;
        this.accountIdx = accountIdx;
        this.registDate = registDate;
    }
}
