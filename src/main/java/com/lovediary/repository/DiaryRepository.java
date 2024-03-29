package com.lovediary.repository;

import com.lovediary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * DiaryRepository
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-29          JJY             최초 등록
 **/
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByAccountIdx(Long idx);
}


