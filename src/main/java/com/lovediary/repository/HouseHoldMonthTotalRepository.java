package com.lovediary.repository;

import com.lovediary.dto.PlusAndMinus;
import com.lovediary.entity.HouseholdLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseHoldMonthTotalRepository extends JpaRepository<HouseholdLedger, Long> {
    @Query(nativeQuery = true, value =
            "SELECT SUM(IF(h.type = 'I', h.amount, 0)) AS plusAmount," +
            "SUM(IF(h.type = 'O', h.amount, 0)) AS minusAmount " +
            "FROM household_ledger h " +
            "WHERE MONTH(h.due_date) = MONTH(CURRENT_DATE()) ")
    PlusAndMinus calculateMonthAmount();

}