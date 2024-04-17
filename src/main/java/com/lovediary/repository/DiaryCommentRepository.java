package com.lovediary.repository;

import com.lovediary.entity.DiaryComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * DiaryCommentRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-01
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-01          JJY             최초 등록
 **/
public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
    List<DiaryComment> findByCoupleDiaryIdxAndDeleteYnOrderByIdxDesc(Long coupleDiaryIdx, Character deleteYn);
}
