package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

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
public class Diary {
    @Id
    private Long idx;

    @Column
    private Long coupleIdx;

    @Column
    private Long emotionIdx;

    @Column
    private Long categoryIdx;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private Long accountIdx;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Builder
    public Diary(Long idx, Long coupleIdx, Long emotionIdx, Long categoryIdx, String title, String contents, Long accountIdx,Timestamp registDate) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.emotionIdx = emotionIdx;
        this.categoryIdx = categoryIdx;
        this.title = title;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.registDate = registDate;
    }
}
