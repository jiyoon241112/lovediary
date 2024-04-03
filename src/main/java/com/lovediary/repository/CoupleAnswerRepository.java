package com.lovediary.repository;

import com.lovediary.entity.CoupleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

/**
 * 
 * CoupleAnswerRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
public interface CoupleAnswerRepository extends JpaRepository<CoupleAnswer, Long> {
    List<CoupleAnswer> findByCoupleIdxOrderByIdxDesc(Long coupleIdx);
    boolean existsByCoupleIdxAndQuestionDate(Long coupleIdx, Date questionDate);
    CoupleAnswer findByCoupleIdxAndQuestionDate(Long coupleIdx, Date questionDate);
}
