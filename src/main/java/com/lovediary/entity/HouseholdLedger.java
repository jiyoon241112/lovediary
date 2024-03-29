package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * HouseholdLedger
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-03-29          JJY             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="household_ledger")
public class HouseholdLedger {
    @Id
    private Long idx;

    @Column
    private Long categoryIdx;

    @Column
    private Character type;

    @Column
    private String contents;

    @Column
    private Long amount;

    @Column
    private Long budget;

    @Column
    private Timestamp dueDate;

    @Column
    private Long accountIdx;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp modifyDate;

    @Column
    private Timestamp deleteDate;

    @Builder
    public HouseholdLedger(Long idx, Long categoryIdx, Character type, String contents, Long amount, Long budget, Timestamp dueDate,
                           Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.categoryIdx = categoryIdx;
        this.type = type;
        this.contents = contents;
        this.amount = amount;
        this.budget = budget;
        this.dueDate = dueDate;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
