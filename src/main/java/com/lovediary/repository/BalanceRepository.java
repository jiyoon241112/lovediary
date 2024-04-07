package com.lovediary.repository;

import com.lovediary.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * BalanceRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findByTitleContainsAndDeleteYnOrderByIdxDesc(String title, Character deleteYn);
}
