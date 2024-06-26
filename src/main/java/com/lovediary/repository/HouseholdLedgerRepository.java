package com.lovediary.repository;

import com.lovediary.entity.HouseholdLedger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * HouseholdLedgerRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-03-29          JJY             최초 등록
 **/

public interface HouseholdLedgerRepository extends JpaRepository<HouseholdLedger, Long> {
    List<HouseholdLedger> findByAccountIdxIn(List<Long> accountIdx);

    List<HouseholdLedger> findByAccountIdxInAndType(List<Long> accountIdx, Character type);
}
