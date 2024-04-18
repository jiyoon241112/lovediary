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
 * Chatting
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-15
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-15          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting extends JoinAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long emoticonIdx;

    @Column
    private Long imageIdx;

    @Column(length = 300)
    private String contents;

    @Column
    private Character readYn;

    @Column
    private Timestamp readDate;

    @Column(name = "account_idx", insertable = false, updatable=false)
    private Long accountIdx;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp deleteDate;

    @Builder
    public Chatting(Long idx, Long emoticonIdx, Long imageIdx, String contents, Character readYn, Timestamp readDate, Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp deleteDate, Account account) {
        this.idx = idx;
        this.emoticonIdx = emoticonIdx;
        this.imageIdx = imageIdx;
        this.contents = contents;
        this.readYn = readYn == null ? 'Y' : readYn;
        this.readDate = readDate;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.accountIdx = accountIdx;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.deleteDate = deleteDate;
        this.setAccount(account);
    }
}
