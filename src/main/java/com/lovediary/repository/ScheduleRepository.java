package com.lovediary.repository;

import com.lovediary.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 *
 * ScheduleRepository
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          JJY             최초 등록
 **/

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value =
            "SELECT s " +
            "FROM Schedule s " +
            "WHERE DATE_FORMAT(s.startDate, '%Y-%m-%d') = :startDate " +
            "AND s.accountIdx = :idx " +
            "AND s.deleteYn = 'N'")
    List<Schedule> findByAccountIdxAndStartDate(Long idx, String startDate);
}
