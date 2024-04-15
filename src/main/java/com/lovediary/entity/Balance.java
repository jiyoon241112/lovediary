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
 * Balance
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
public class Balance extends JoinAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100)
    private String title;

    @Column(length = 300)
    private String contents;

    @Column(name = "account_idx", insertable = false, updatable=false)
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
    public Balance(Long idx, String title, String contents, Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate, Account account) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
        this.setAccount(account);
    }
}
