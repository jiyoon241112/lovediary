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
 * DriveFile
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-10
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-10          HTH             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriveFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long fileIdx;

    @Column
    private Long driveIdx;

    @Column
    private Long size;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp deleteDate;

    @Builder
    public DriveFile(Long idx, Long fileIdx, Long driveIdx, Long size, Character deleteYn, Timestamp registDate, Timestamp deleteDate) {
        this.idx = idx;
        this.fileIdx = fileIdx;
        this.driveIdx = driveIdx;
        this.size = size;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.deleteDate = deleteDate;
    }
}
