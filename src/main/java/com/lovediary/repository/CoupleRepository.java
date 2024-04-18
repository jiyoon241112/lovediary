package com.lovediary.repository;

import com.lovediary.entity.Couple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query(nativeQuery = true, value =
            "SELECT TIMESTAMPDIFF(DAY, start_date, NOW()) " +
            "FROM couple " +
            "WHERE idx = :idx")
    Integer findDdayByIdx(Long idx);

    Couple findByCode(String code);
}
