package com.lovediary.dto;

import com.lovediary.entity.File;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * FileDto
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
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
    private Long idx;
    private String path;
    private String fileName;
    private Timestamp registDate;

    public File toEntity() {
        return File.builder()
                .idx(idx)
                .path(path)
                .fileName(fileName)
                .registDate(registDate)
                .build();
    }

    @Builder
    public FileDto(Long idx, String path, String fileName, Timestamp registDate) {
        this.idx = idx;
        this.path = path;
        this.fileName = fileName;
        this.registDate = registDate;
    }
}
