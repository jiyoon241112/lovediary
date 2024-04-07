package com.lovediary.repository;

import com.lovediary.entity.BalanceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * BalanceItemRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
public interface BalanceItemRepository extends JpaRepository<BalanceItem, Long> {
    List<BalanceItem> findByBalanceIdxOrderByIdx(Long balanceIdx);
}
