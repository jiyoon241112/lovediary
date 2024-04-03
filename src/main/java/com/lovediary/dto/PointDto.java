package com.lovediary.dto;

import com.lovediary.entity.Point;
import lombok.*;

import java.sql.Timestamp;

/**
 * 
 * PointDto
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointDto {
    private Long idx;
    private Long coupleIdx;
    private String contents;
    private Long point;
    private Timestamp registDate;

    public Point toEntity() {
        return Point.builder()
                .idx(idx)
                .coupleIdx(coupleIdx)
                .contents(contents)
                .point(point)
                .registDate(registDate)
                .build();
    }

    @Builder
    public PointDto(Long idx, Long coupleIdx, String contents, Long point, Timestamp registDate) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.contents = contents;
        this.point = point;
        this.registDate = registDate;
    }
}
