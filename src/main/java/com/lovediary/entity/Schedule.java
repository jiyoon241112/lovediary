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
 * Schedule
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="schedule")
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long bucketIdx;

    @Column
    private Long bucketItemIdx;

    @Column
    private Timestamp startDate;

    @Column
    private Timestamp endDate;

    @Column
    private String title;

    @Column
    private String address;

    @Column
    private String addressDetail;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private Long coupleIdx;

    @Column
    private Long accountIdx;

    @Column
    private Character deleteYn;

    @CreatedDate
    @Column(updatable = false)
    private Timestamp registDate;

    @Column
    private Timestamp modifyDate;

    @Column
    private Timestamp deleteDate;

    @Builder
    public Schedule(Long idx, Long bucketIdx, Long bucketItemIdx, Timestamp startDate, Timestamp endDate, String title,
                    String address, String addressDetail, Float latitude, Float longitude, Long coupleIdx,
                    Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.bucketIdx = bucketIdx;
        this.bucketItemIdx = bucketItemIdx;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.coupleIdx = coupleIdx;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
