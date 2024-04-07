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
 * Place
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          JJY             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="date_place")
public class Place {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long themeIdx;

    @Column
    private Character type;

    @Column
    private String imageList;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String address;

    @Column
    private String addressDetail;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private String homepage;

    @Column
    private Long accountIdx;

    @Column
    private Character openYn;

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
    public Place(Long idx, Long themeIdx, Character type, String imageList, String title, String contents, String address,
                 String addressDetail, Float latitude, Float longitude, String homepage, Long accountIdx, Character openYn,
                 Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.themeIdx = themeIdx;
        this.type = type;
        this.imageList = imageList;
        this.title = title;
        this.contents = contents;
        this.address = address;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.homepage = homepage;
        this.accountIdx = accountIdx;
        this.openYn = openYn;
        this.deleteYn = deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
