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
 * File
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 100)
    private String path;

    @Column(length = 100)
    private String fileName;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Builder
    public File(Long idx, String path, String fileName, Timestamp registDate) {
        this.idx = idx;
        this.path = path;
        this.fileName = fileName;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
    }
}
