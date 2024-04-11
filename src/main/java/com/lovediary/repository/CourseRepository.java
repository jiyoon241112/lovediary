package com.lovediary.repository;

import com.lovediary.dto.BookMarkCourse;
import com.lovediary.dto.CourseList;
import com.lovediary.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 
 * CourseRepository
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-08
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-08          JJY             최초 등록
 **/

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(nativeQuery = true, value =
            "SELECT a.idx, a.title, c.address, b.date_place_idx, b.place_cnt, d.course_idx, a.theme_idx  " +
            "FROM date_course AS a " +
            "INNER JOIN ( " +
            "    SELECT date_course_idx, MIN(date_place_idx) AS date_place_idx, COUNT(date_place_idx) AS place_cnt " +
            "    FROM date_course_item " +
            "    GROUP BY date_course_idx " +
            ") AS b " +
            "ON a.idx = b.date_course_idx " +
            "INNER JOIN date_place AS c " +
            "ON b.date_place_idx = c.idx " +
            "LEFT JOIN( " +
            " SELECT course_idx " +
            " FROM date_course_bookmark   " +
            ") AS d " +
            "ON a.idx = d.course_idx  " +
            "WHERE a.delete_yn = 'N'  " +
            "AND a.theme_idx = :idx " +
            "AND a.title LIKE CONCAT('%', :keyword, '%')")
    List<BookMarkCourse> courseList(Long idx, String keyword);

    @Query(nativeQuery = true, value =
            "SELECT a.idx, a.title, c.address, b.date_place_idx, b.place_cnt, d.course_idx, a.theme_idx  " +
            "FROM date_course AS a " +
            "INNER JOIN ( " +
            "    SELECT date_course_idx, MIN(date_place_idx) AS date_place_idx, COUNT(date_place_idx) AS place_cnt " +
            "    FROM date_course_item " +
            "    GROUP BY date_course_idx " +
            ") AS b " +
            "ON a.idx = b.date_course_idx " +
            "INNER JOIN date_place AS c " +
            "ON b.date_place_idx = c.idx " +
            "LEFT JOIN( " +
            " SELECT course_idx " +
            " FROM date_course_bookmark   " +
            ") AS d " +
            "ON a.idx = d.course_idx  " +
            "WHERE a.delete_yn = 'N'  " +
            "AND a.title LIKE CONCAT('%', :keyword, '%')")
    List<BookMarkCourse> courseList(String keyword);

    @Query(nativeQuery = true, value =
            "SELECT dc.idx, dc.theme_idx , dc.title AS courseTitle, dp.title, dp.contents, dp.homepage " +
            "FROM date_course dc  " +
            "INNER JOIN( " +
            " SELECT date_course_idx, date_place_idx " +
            " FROM date_course_item " +
            ") AS dci " +
            "ON dci.date_course_idx = dc.idx  " +
            "INNER JOIN( " +
            " SELECT idx,title, contents, homepage " +
            " FROM date_place  " +
            " WHERE delete_yn  = 'N' " +
            ") AS dp " +
            "ON dp.idx = dci.date_place_idx " +
            "WHERE dc.delete_yn = 'N'" +
            "AND dc.idx = :idx")
    List<CourseList> courseDetailList(Long idx);

}
