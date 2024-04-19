package com.lovediary.repository;

import com.lovediary.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * EmotionRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-19
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-19          HTH             최초 등록
 **/
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
