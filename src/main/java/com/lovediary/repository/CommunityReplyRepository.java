package com.lovediary.repository;

import com.lovediary.entity.CommunityReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityReplyRepository extends JpaRepository<CommunityReply, Long> {
    @Query("SELECT A, B " +
            "FROM CommunityReply A " +
            "LEFT JOIN Account B ON A.accountIdx = B.idx " +
            "WHERE A.communityIdx = :communityIdx " +
            "AND ((:replyIdx IS NULL AND A.replyIdx IS NULL) OR (:replyIdx IS NOT NULL AND A.replyIdx = :replyIdx)) " +
            "AND A.deleteYn = :deleteYn")
    List<CommunityReply> findByCommunityIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(Long communityIdx, Long replyIdx, Character deleteYn);
}
