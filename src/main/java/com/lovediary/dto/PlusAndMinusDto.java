package com.lovediary.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PlusAndMinusDto {
    private Long plusAmount;
    private Long minusAmount;

    @Builder
    public PlusAndMinusDto(Long plusAmount, Long minusAmount) {
        this.plusAmount = plusAmount;
        this.minusAmount = minusAmount;
    }
}
