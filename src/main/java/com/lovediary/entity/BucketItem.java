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
 * BucketItem
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="bucket_item")
public class BucketItem extends JoinAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long bucketIdx;

    @Column
    private Timestamp targetDate;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String address;

    @Column
    private Character achieveYn;

    @Column
    private String addressDetail;

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private Timestamp achieveDate;

    @Column(name = "account_idx", insertable = false, updatable=false)
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
    public BucketItem(Long idx, Long bucketIdx, Timestamp targetDate, String title, String contents, String address,
                      Character achieveYn, String addressDetail, Float latitude, Float longitude, Timestamp achieveDate,
                      Long accountIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate
                        , Account account) {
        this.idx = idx;
        this.bucketIdx = bucketIdx;
        this.targetDate = targetDate;
        this.title = title;
        this.contents = contents;
        this.address = address;
        this.achieveYn = achieveYn == null ? 'N' : achieveYn;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.achieveDate = achieveDate;
        this.accountIdx = accountIdx;
        this.deleteYn = deleteYn == null ? 'N' : deleteYn;
        this.registDate = registDate == null ? new Timestamp(System.currentTimeMillis()) : registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
        this.setAccount(account);
    }
}
