package com.lovediary.repository;

import com.lovediary.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * AlarmRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-26
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-26          HTH             최초 등록
 **/
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByAccountIdx(Long accountIdx);
}
