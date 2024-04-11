package com.lovediary.service;

import com.lovediary.dto.BucketDto;
import com.lovediary.dto.BucketItemDto;
import com.lovediary.dto.DiaryCommentDto;
import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.entity.Bucket;
import com.lovediary.entity.BucketItem;
import com.lovediary.entity.DiaryComment;
import com.lovediary.repository.BucketItemRepository;
import com.lovediary.repository.BucketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * BucketService
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/
@Service
public class BucketService {
    private final BucketRepository bucketRepository;
    private final BucketItemRepository bucketItemRepository;

    public BucketService(BucketRepository bucketRepository, BucketItemRepository bucketItemRepository) {
        this.bucketRepository = bucketRepository;
        this.bucketItemRepository = bucketItemRepository;
    }

    // <버킷리스트 페이지>
    @Transactional
    public List<BucketDto> getList() {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(2L);
        accountIdx.add(3L);

        List<Bucket> bucketList = bucketRepository.findByAccountIdxInOrderByIdxDesc(accountIdx);
        List<BucketDto> resultList = new ArrayList<>();

        for(Bucket bucket : bucketList) {
            resultList.add(convertToDto(bucket));
        }

        return resultList;
    }

    // <버킷리스트 상세 페이지>
    @Transactional
    public BucketDto getOne(Long idx) {
        Optional<Bucket> wrapper = bucketRepository.findById(idx);
        Bucket bucket = wrapper.get();

        return convertToDto(bucket);
    }

    // <버킷리스트 아이템 리스트 페이지>
    @Transactional
    public List<BucketItemDto> getBucketItemList(Long idx) {
        List<BucketItem> itemList = bucketItemRepository.findByBucketIdxOrderByIdxDesc(idx);
        List<BucketItemDto> resultList = new ArrayList<>();

        for(BucketItem bucketItem : itemList) {
            resultList.add(convertToDto(bucketItem));
        }

        return resultList;
    }

    // <버킷리스트 항목 상세 페이지>
    @Transactional
    public BucketItemDto getItemOne(Long idx) {
        Optional<BucketItem> wrapper = bucketItemRepository.findById(idx);
        BucketItem bucketItem = wrapper.get();

        return convertToDto(bucketItem);
    }

    // <버킷리스트 작성(저장)>
    @Transactional
    public Long saveItem(BucketDto bucketDto) {
        return bucketRepository.save(bucketDto.toEntity()).getIdx();
    }

    // <버킷리스트 항목 작성(저장)>
    @Transactional
    public Long saveBucketItem(BucketItemDto bucketItemDto) {
        return bucketItemRepository.save(bucketItemDto.toEntity()).getIdx();
    }

    // 버킷리스트 Dto 변환
    private BucketDto convertToDto(Bucket bucket) {
        return BucketDto.builder()
                .idx(bucket.getIdx())
                .thumbnailIdx(bucket.getThumbnailIdx())
                .title(bucket.getTitle())
                .contents(bucket.getContents())
                .achieveYn(bucket.getAchieveYn())
                .achieveDate(bucket.getAchieveDate())
                .accountIdx(bucket.getAccountIdx())
                .deleteYn(bucket.getDeleteYn())
                .registDate(bucket.getRegistDate())
                .modifyDate(bucket.getModifyDate())
                .deleteDate(bucket.getDeleteDate())
                .build();
    }

    // 버킷리스트 항목 Dto 변환
    private BucketItemDto convertToDto(BucketItem bucketItem) {
        return BucketItemDto.builder()
                .idx(bucketItem.getIdx())
                .bucketIdx(bucketItem.getBucketIdx())
                .targetDate(bucketItem.getTargetDate())
                .title(bucketItem.getTitle())
                .contents(bucketItem.getContents())
                .address(bucketItem.getAddress())
                .achieveYn(bucketItem.getAchieveYn())
                .addressDetail(bucketItem.getAddressDetail())
                .latitude(bucketItem.getLatitude())
                .longitude(bucketItem.getLongitude())
                .achieveDate(bucketItem.getAchieveDate())
                .accountIdx(bucketItem.getAccountIdx())
                .deleteYn(bucketItem.getDeleteYn())
                .registDate(bucketItem.getRegistDate())
                .modifyDate(bucketItem.getModifyDate())
                .deleteDate(bucketItem.getDeleteDate())
                .build();
    }
}
