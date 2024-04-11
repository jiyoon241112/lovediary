package com.lovediary.dto;

import com.lovediary.entity.DriveFile;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * DriveFileDto
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
@Setter
@ToString
@NoArgsConstructor
public class DriveFileDto {
    private Long idx;
    private Long fileIdx;
    private Long driveIdx;
    private Long size;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp deleteDate;

    public DriveFile toEntity() {
        return DriveFile.builder()
                .idx(idx)
                .fileIdx(fileIdx)
                .driveIdx(driveIdx)
                .size(size)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public DriveFileDto(Long idx, Long fileIdx, Long driveIdx, Long size, Character deleteYn, Timestamp registDate, Timestamp deleteDate) {
        this.idx = idx;
        this.fileIdx = fileIdx;
        this.driveIdx = driveIdx;
        this.size = size;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.deleteDate = deleteDate;
    }
}
