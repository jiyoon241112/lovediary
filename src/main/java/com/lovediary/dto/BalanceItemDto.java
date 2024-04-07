package com.lovediary.dto;

import com.lovediary.entity.BalanceItem;
import lombok.*;

/**
 *
 * BalanceItemDto
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
public class BalanceItemDto {
    private Long idx;
    private Long balanceIdx;
    private String title;

    public BalanceItem toEntity() {
        return BalanceItem.builder()
                .idx(idx)
                .balanceIdx(balanceIdx)
                .title(title)
                .build();
    }

    @Builder
    public BalanceItemDto(Long idx, Long balanceIdx, String title) {
        this.idx = idx;
        this.balanceIdx = balanceIdx;
        this.title = title;
    }
}
