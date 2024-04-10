package com.lovediary.dto;

import com.lovediary.entity.Drive;
import lombok.*;

/**
 *
 * DriveDto
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
public class DriveDto {
    private Long idx;
    private Long coupleIdx;
    private String path;
    private Long useSize;
    private Long totalSize;

    public Drive toEntity() {
        return Drive.builder()
                .idx(idx)
                .coupleIdx(coupleIdx)
                .path(path)
                .useSize(useSize)
                .totalSize(totalSize)
                .build();
    }

    @Builder
    public DriveDto(Long idx, Long coupleIdx, String path, Long useSize, Long totalSize) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.path = path;
        this.useSize = useSize;
        this.totalSize = totalSize;
    }
}
