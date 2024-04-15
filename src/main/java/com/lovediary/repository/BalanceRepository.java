package com.lovediary.repository;

import com.lovediary.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query("SELECT A, B " +
            "FROM Balance A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.title LIKE CONCAT('%', :title, '%') " +
            "AND A.deleteYn = :deleteYn " +
            "ORDER BY A.idx DESC ")
    List<Balance> findByTitleContainsAndDeleteYnOrderByIdxDesc(String title, Character deleteYn);
}
