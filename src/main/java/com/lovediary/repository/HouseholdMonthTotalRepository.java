package com.lovediary.repository;

import com.lovediary.dto.PlusAndMinus;
import com.lovediary.entity.HouseholdLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 
 * HouseholdMonthTotalRepository
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
public interface HouseholdMonthTotalRepository extends JpaRepository<HouseholdLedger, Long> {
    @Query(nativeQuery = true, value =
            "SELECT SUM(IF(h.type = 'I', h.amount, 0)) AS plusAmount," +
            "       SUM(IF(h.type = 'O', h.amount, 0)) AS minusAmount " +
            "FROM household_ledger h " +
            "WHERE MONTH(h.due_date) = MONTH(CURRENT_DATE()) ")
    PlusAndMinus calculateMonthAmount();

    @Query(nativeQuery = true, value =
            "SELECT MONTH(h.due_date) AS MON, " +
            "       SUM(IF(h.type = 'I', h.amount, 0)) AS plusAmount," +
            "       SUM(IF(h.type = 'O', h.amount, 0)) AS minusAmount," +
            "       SUM(IF(h.type = 'I', h.amount, 0)) - SUM(IF(h.type = 'O', h.amount, 0)) AS totalAmount " +
            "FROM household_ledger h " +
            "GROUP BY MON ")
    List<PlusAndMinus> calculateTotalAmount();

}