package com.lovediary.repository;

import com.lovediary.entity.DriveFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * DriveFileRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-10
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-10          HTH             최초 등록
 **/
public interface DriveFileRepository extends JpaRepository<DriveFile, Long> {
    List<DriveFile> findByDriveIdxAndDeleteYnOrderByIdxDesc(Long driveIdx, Character deleteYn);
}
