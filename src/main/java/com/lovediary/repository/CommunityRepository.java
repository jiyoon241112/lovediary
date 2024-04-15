package com.lovediary.repository;

import com.lovediary.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    @Query("SELECT A, B " +
            "FROM Community A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.title LIKE CONCAT('%', :title, '%') " +
            "AND A.deleteYn = :deleteYn " +
            "ORDER BY A.idx DESC ")
    List<Community> findByTitleContainsAndDeleteYnOrderByIdxDesc(String title, Character deleteYn);
}
