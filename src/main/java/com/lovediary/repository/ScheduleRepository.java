package com.lovediary.repository;

import com.lovediary.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.lovediary.dto.CalendarList;

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

    @Query(nativeQuery = true, value =
            "SELECT YEAR(start_date) AS year, MONTH(start_date) AS month, DAY(start_date) AS day " +
            "FROM schedule  " +
            "WHERE delete_yn = 'N' " +
            "GROUP BY YEAR(start_date), MONTH(start_date), DAY(start_date)")
    List<CalendarList> getCalendarList();
}
