package com.lovediary.repository;

import com.lovediary.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * CommunityRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByTitleContainsAndDeleteYnOrderByIdxDesc(String title, Character deleteYn);
}
