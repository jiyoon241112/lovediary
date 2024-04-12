package com.lovediary.dto;

import com.lovediary.entity.Couple;
import com.lovediary.entity.Schedule;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * ScheduleDto
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          JJY             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScheduleDto {
    private Long idx;
    private Long bucketIdx;
    private Long bucketItemIdx;
    private Timestamp startDate;
    private Timestamp endDate;
    private String startDateStr;
    private String endDateStr;
    private String title;
    private String address;
    private String addressDetail;
    private Float latitude;
    private Float longitude;
    private Long coupleIdx;
    private Long accountIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public Schedule toEntity() {
        return Schedule.builder()
                .idx(idx)
                .bucketIdx(bucketIdx)
                .bucketItemIdx(bucketItemIdx)
                .startDate(startDate)
                .endDate(endDate)
                .title(title)
                .address(address)
                .addressDetail(addressDetail)
                .latitude(latitude)
                .longitude(longitude)
                .coupleIdx(coupleIdx)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public ScheduleDto(Long idx, Long bucketIdx, Long bucketItemIdx, Timestamp startDate, Timestamp endDate, String title,
                    String address, String addressDetail, Float latitude, Float longitude, Long coupleIdx,
                    Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

        this.idx = idx;
        this.bucketIdx = bucketIdx;
        this.bucketItemIdx = bucketItemIdx;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateStr = startDate == null ? null : dateFormat.format(new java.util.Date(startDate.getTime()));
        this.endDateStr = endDate == null ? null : dateFormat.format(new java.util.Date(endDate.getTime()));
        this.title = title;
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.coupleIdx = coupleIdx;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
