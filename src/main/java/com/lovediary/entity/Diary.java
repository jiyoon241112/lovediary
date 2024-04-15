package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * Diary
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
@Table(name="couple_diary")
public class Diary extends JoinAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long coupleIdx;

    @Column
    private Long emotionIdx;

    @Column
    private String title;

    @Column
    private String contents;

    @Column(name = "account_idx", insertable = false, updatable=false)
    private Long accountIdx;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Builder
    public Diary(Long idx, Long coupleIdx, Long emotionIdx, String title, String contents,
                 Long accountIdx,Timestamp registDate, Account account) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.emotionIdx = emotionIdx;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.setAccount(account);
    }
}
