package com.lovediary.repository;

import com.lovediary.entity.CoupleQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * CoupleQuestionRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
public interface CoupleQuestionRepository extends JpaRepository<CoupleQuestion, Long> {
    @Query(nativeQuery = true, value = "SELECT MAX(idx) FROM couple_question")
    Long findIdxMax();
}
