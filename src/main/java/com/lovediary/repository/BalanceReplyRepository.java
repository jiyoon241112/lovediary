package com.lovediary.repository;

import com.lovediary.entity.BalanceReply;
import org.springframework.data.jpa.repository.JpaRepository;

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
    List<BalanceReply> findByBalanceIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(Long balanceIdx, Long ReplyIdx, Character deleteYn);
}
