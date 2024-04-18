package com.lovediary.repository;

import com.lovediary.dto.BookMarkTheme;
import com.lovediary.entity.ThemeBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * 
 * ThemeBookMarkRepository
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-13
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-13          JJY             최초 등록
 **/
public interface ThemeBookMarkRepository extends JpaRepository<ThemeBookMark, Long> {
    //즐겨찾기 조회
    @Query(nativeQuery = true, value =
            "SELECT t.idx, t.image_idx AS imageIdx , t.name, tb.theme_idx AS themeIdx " +
            "FROM theme t " +
            "LEFT JOIN( " +
            "  SELECT theme_idx, account_idx " +
            "  FROM theme_bookmark  " +
            ") AS tb " +
            "ON t.idx = tb.theme_idx " +
            "WHERE tb.account_idx = :accountIdx OR tb.account_idx IS NULL " +
            "ORDER  BY tb.account_idx DESC ")
    List<BookMarkTheme> bookMarkList(Long accountIdx);
}
