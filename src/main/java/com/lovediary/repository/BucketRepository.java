package com.lovediary.repository;

import com.lovediary.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * BucketRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-09
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-09          JJY             최초 등록
 **/

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    @Query("SELECT A, B " +
            "FROM Bucket A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.accountIdx IN (:accountIdx) " +
            "AND A.deleteYn = 'N' " +
            "ORDER BY A.idx DESC")
    List<Bucket> findByAccountIdxInOrderByIdxDesc(List<Long> accountIdx);
}
