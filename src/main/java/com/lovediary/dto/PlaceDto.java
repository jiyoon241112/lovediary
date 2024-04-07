package com.lovediary.dto;

import com.lovediary.entity.Diary;
import com.lovediary.entity.Place;
import lombok.*;

import java.sql.Timestamp;

/**
 *
 * PlaceDto
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
@Setter
@ToString
@NoArgsConstructor
public class PlaceDto {
    private Long idx;
    private Long themeIdx;
    private Character type;
    private String imageList;
    private String title;
    private String contents;
    private String address;
    private String addressDetail;
    private Float latitude;
    private Float longitude;
    private String homepage;
    private Long accountIdx;
    private Character openYn;
    private Character deleteYn;
    private Timestamp registDate;
    private Timestamp modifyDate;
    private Timestamp deleteDate;

    public Place toEntity() {
        return Place.builder()
                .idx(idx)
                .themeIdx(themeIdx)
                .type(type)
                .imageList(imageList)
                .title(title)
                .contents(contents)
                .address(address)
                .addressDetail(addressDetail)
                .latitude(latitude)
                .longitude(longitude)
                .homepage(homepage)
                .accountIdx(accountIdx)
                .openYn(openYn)
                .deleteYn(deleteYn)
                .registDate(registDate)
                .modifyDate(modifyDate)
                .deleteDate(deleteDate)
                .build();
    }

    @Builder
    public PlaceDto(Long idx, Long themeIdx, Character type, String imageList, String title, String contents,String address,
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
        this.registDate = registDate;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }
}
