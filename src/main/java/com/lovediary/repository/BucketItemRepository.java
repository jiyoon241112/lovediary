package com.lovediary.repository;

import com.lovediary.entity.BucketItem;
import com.lovediary.entity.DiaryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * BucketItemRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/

public interface BucketItemRepository extends JpaRepository<BucketItem, Long> {
    @Query("SELECT A, B " +
            "FROM BucketItem A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.deleteYn = 'N' " +
            "AND A.bucketIdx = :bucketIdx " +
            "ORDER BY A.idx DESC")
    List<BucketItem> findByBucketIdxOrderByIdxDesc(Long bucketIdx);
}
