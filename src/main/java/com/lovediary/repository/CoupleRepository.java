package com.lovediary.repository;

import com.lovediary.entity.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * CoupleRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-30
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-30          HTH             최초 등록
 **/
public interface CoupleRepository extends JpaRepository<Couple, Long> {
}
