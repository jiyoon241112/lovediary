package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.BucketItem;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * BucketItemDto
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
@Setter
@ToString
@NoArgsConstructor
public class BucketItemDto {
    private Long idx;
    private Long bucketIdx;
    private Timestamp targetDate;
    private String title;
    private String contents;
    private String address;
    private Character achieveYn;
    private String addressDetail;
    private Float latitude;
    private Float longitude;
    private Timestamp achieveDate;
    private Long accountIdx;
    private String accountName;
    private Long profileIdx;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public BucketItem toEntity() {
        Account account = Account.builder()
                .idx(accountIdx)
                .name(accountName)
                .profileIdx(profileIdx)
                .build();

        return BucketItem.builder()
                .idx(idx)
                .bucketIdx(bucketIdx)
                .targetDate(targetDate)
                .title(title)
                .contents(contents)
                .address(address)
                .achieveYn(achieveYn)
                .addressDetail(addressDetail)
                .latitude(latitude)
                .longitude(longitude)
                .achieveDate(achieveDate)
                .accountIdx(accountIdx)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .account(account)
                .build();
    }

    @Builder
    public BucketItemDto(Long idx, Long bucketIdx, Timestamp targetDate, String title, String contents, String address,
                         Character achieveYn, String addressDetail, Float latitude, Float longitude, Timestamp achieveDate,
                         Long accountIdx, String accountName, Long profileIdx, Character deleteYn, Timestamp registDate, Timestamp modifyDate, Timestamp deleteDate) {
        this.idx = idx;
        this.bucketIdx = bucketIdx;
        this.targetDate = targetDate;
        this.title = title;
        this.contents = contents;
        this.address = address;
        this.achieveYn = achieveYn;
        this.addressDetail = addressDetail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.achieveDate = achieveDate;
        this.accountIdx = accountIdx;
        this.accountName = accountName;
        this.profileIdx = profileIdx;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
