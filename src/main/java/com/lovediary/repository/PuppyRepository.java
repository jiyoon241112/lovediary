package com.lovediary.repository;

import com.lovediary.entity.Puppy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * PuppyRepository
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
public interface PuppyRepository extends JpaRepository<Puppy, Long> {
    Puppy findByCoupleIdx(Long coupleIdx);
}
