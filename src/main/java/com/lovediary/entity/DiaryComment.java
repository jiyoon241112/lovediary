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
 * DiaryComment
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-01
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-01          JJY             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="couple_diary_reply")
public class DiaryComment {
    @Id
    private Long idx;

    @Column
    private Long coupleDiaryIdx;

    @Column
    private String contents;

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
    public DiaryComment(Long idx, Long coupleDiaryIdx, String contents, Long accountIdx, Character deleteYn, Timestamp registDate,
                        Timestamp modifyDate, Timestamp deleteDate, Diary diary) {
        this.idx = idx;
        this.coupleDiaryIdx = coupleDiaryIdx;
        this.contents = contents;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
