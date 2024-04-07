package com.lovediary.repository;

import com.lovediary.entity.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityReplyRepository extends JpaRepository<CommunityReply, Long> {
    List<CommunityReply> findByCommunityIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(Long communityIdx, Long replyIdx, Character deleteYn);
}
