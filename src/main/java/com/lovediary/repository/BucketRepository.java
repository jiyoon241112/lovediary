package com.lovediary.repository;

import com.lovediary.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<Bucket> findByAccountIdxInOrderByIdxDesc(List<Long> accountIdx);
}
