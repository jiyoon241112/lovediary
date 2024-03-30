package com.lovediary.values;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * SessionData
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
@Setter
@NoArgsConstructor
public class SessionData {
    private Long coupleIdx;
    private Long accountIdx;
    private Long partnerIdx;

    @Builder
    public SessionData(Long coupleIdx, Long accountIdx, Long partnerIdx) {
        this.coupleIdx = coupleIdx;
        this.accountIdx = accountIdx;
        this.partnerIdx = partnerIdx;
    }
}
