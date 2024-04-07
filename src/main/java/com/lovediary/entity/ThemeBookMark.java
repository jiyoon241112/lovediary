package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="theme_bookmark")
public class ThemeBookMark {
    @Id
    private Long idx;

    @Column
    private Long themeIdx;

    @Column
    private Long accountIdx;

    @Builder
    public ThemeBookMark(Long idx, Long themeIdx, Long accountIdx) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.accountIdx = accountIdx;
    }
}
