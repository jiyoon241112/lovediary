package com.lovediary.repository;

import com.lovediary.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 
 * PointRepository
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByCoupleIdxOrderByIdx(Long coupleIdx);
}
