package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 
 * Emotion
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-19
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-19          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emotion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_idx", nullable = false)
    private File image;

    @Builder
    public Emotion(Long idx, File image) {
        this.idx = idx;
        this.image = image;
    }
}