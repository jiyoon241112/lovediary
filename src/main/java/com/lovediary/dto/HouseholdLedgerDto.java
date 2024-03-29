package com.lovediary.dto;

import com.lovediary.entity.HouseholdLedger;
import lombok.*;

import java.sql.Timestamp;

public class HouseholdLedgerDto {
    private Long idx;
    private Long categoryIdx;
    private Character type;
    private String contents;
    private Long amount;
    private Long budget;
    private Timestamp dueDate;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public HouseholdLedger toEntity() {
        return HouseholdLedger.builder()
                .idx(idx)
                .categoryIdx(categoryIdx)
                .type(type)
                .contents(contents)
                .amount(amount)
                .budget(budget)
                .dueDate(dueDate)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public HouseholdLedgerDto(Long idx, Long categoryIdx, Character type, String contents, Long amount, Timestamp dueDate, Long accountIdx,
                              Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.categoryIdx = categoryIdx;
        this.type = type;
        this.contents = contents;
        this.amount = amount;
        this.dueDate = dueDate;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
