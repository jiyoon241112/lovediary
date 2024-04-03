package com.lovediary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;

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
