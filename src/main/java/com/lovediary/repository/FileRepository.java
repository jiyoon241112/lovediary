package com.lovediary.repository;

import com.lovediary.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * FileRepository
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          HTH             최초 등록
 **/
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByIdxInOrderByIdxDesc(List<Long> idx);
}
