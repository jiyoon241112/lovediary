package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Course {
    @Id
    private Long idx;

    @Column
    private Long imageIdx;

    @Column
    private String name;

    @Builder
    public Course(Long idx, Long imageIdx, String name) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.name = name;
    }

}
