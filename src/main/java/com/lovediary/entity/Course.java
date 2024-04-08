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

import java.sql.Timestamp;

/**
 *
 * Course
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="date_course")
public class Course {
    @Id
    private Long idx;

    @Column
    private Long themeIdx;

    @Column
    private String title;

    @Column
    private Long accountIdx;

    @Column
    private Character openYn;

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
    public Course(Long idx, Long themeIdx, String title, Long accountIdx, Character openYn, Character deleteYn, Timestamp registDate,
                  Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.title = title;
        this.accountIdx = accountIdx;
        this.openYn = openYn;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

}
