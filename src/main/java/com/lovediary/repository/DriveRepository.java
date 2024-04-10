package com.lovediary.repository;

import com.lovediary.entity.Drive;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * DriveRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-10
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-10          HTH             최초 등록
 **/
public interface DriveRepository extends JpaRepository<Drive, Long> {
    Drive findByCoupleIdx(Long coupleIdx);
}
