package com.lovediary.repository;

import com.lovediary.entity.BalanceReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 
 * BalanceReplyRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
public interface BalanceReplyRepository extends JpaRepository<BalanceReply, Long> {
    @Query("SELECT A, B " +
            "FROM BalanceReply A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.balanceIdx = :balanceIdx " +
            "AND A.deleteYn = :deleteYn " +
            "ORDER BY A.idx DESC ")
    List<BalanceReply> findByBalanceIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(Long balanceIdx, Long ReplyIdx, Character deleteYn);
}
