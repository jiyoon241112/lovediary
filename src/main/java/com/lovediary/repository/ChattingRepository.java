package com.lovediary.repository;

import com.lovediary.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 *
 * ChattingRepository
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-15
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-15          HTH             최초 등록
 **/
public interface ChattingRepository extends JpaRepository<Chatting, Long> {
    @Query("SELECT A, B " +
            "FROM Chatting A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.accountIdx IN (:accountIdx) " +
            "AND DATE_FORMAT(A.registDate, '%Y-%m-%d') = :registDate " +
            "AND A.deleteYn = 'N' " +
            "ORDER BY A.idx DESC")
    List<Chatting> findByAccountIdxInAndRegistDateOrderByIdxDesc(List<Long> accountIdx, String registDate);

    @Query(nativeQuery = true, value =
            "SELECT MAX(DATE_FORMAT(regist_date, '%Y-%m-%d')) " +
            "FROM chatting " +
            "WHERE account_idx IN (:accountIdx) " +
            "AND (:registDate IS NULL OR :registDate = '' OR DATE_FORMAT(regist_date, '%Y-%m-%d') < :registDate) " +
            "AND delete_yn = 'N' ")
    String findByMaxRegistDate(List<Long> accountIdx, String registDate);
}
