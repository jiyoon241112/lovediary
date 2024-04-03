package com.lovediary.dto;

import lombok.*;

/**
 * 
 * PlusAndMinusDto
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlusAndMinusDto {
    private Long plusAmount;
    private Long minusAmount;
    private Long totalAmount;
    private int  mon;

    @Builder
    public PlusAndMinusDto(Long plusAmount, Long minusAmount, Long totalAmount, int mon) {
        this.plusAmount = plusAmount;
        this.minusAmount = minusAmount;
        this.totalAmount = totalAmount;
        this.mon = mon;
    }
}
