package com.lovediary.repository;

import com.lovediary.entity.CoupleAnswerReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoupleAnswerReplyRepository extends JpaRepository<CoupleAnswerReply, Long> {
    List<CoupleAnswerReply> findByCoupleAnswerIdxOrderByIdx(Long coupleAnswerIdx);
}
