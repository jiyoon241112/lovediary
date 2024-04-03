package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * Theme
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
@Table(name="theme")
public class Theme {
    @Id
    private Long idx;

    @Column
    private Long imageIdx;

    @Column
    private String name;

    @Builder
    public Theme(Long idx, Long imageIdx, String name) {
        this.idx = idx;
        this.imageIdx = imageIdx;
        this.name = name;
    }
}
