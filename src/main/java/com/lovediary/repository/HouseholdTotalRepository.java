package com.lovediary.repository;

import com.lovediary.entity.HouseholdLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

/**
 * 
 * HouseholdTotalRepository
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
public interface HouseholdTotalRepository extends JpaRepository<HouseholdLedger, Long> {
    @Query("SELECT SUM( " +
            " CASE " +
            "  WHEN h.type = 'O' THEN CONCAT('-', h.amount) " +
            "  WHEN h.type = 'I' THEN CONCAT('+', h.amount) " +
            " END ) as total_amount " +
            " FROM HouseholdLedger h")

    BigDecimal calculateTotalAmount();

}
