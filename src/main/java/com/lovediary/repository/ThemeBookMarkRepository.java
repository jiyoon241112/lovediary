package com.lovediary.repository;

import com.lovediary.entity.ThemeBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThemeBookMarkRepository extends JpaRepository<ThemeBookMark, Long> {
    //즐겨찾기 조회
    @Query(nativeQuery = true, value =
            "SELECT t.idx, t.image_idx , t.name, tb.theme_idx " +
            "FROM theme t " +
            "LEFT JOIN( " +
            "  SELECT theme_idx, account_idx " +
            "  FROM theme_bookmark  " +
            ") AS tb " +
            "ON t.idx = tb.theme_idx " +
            "WHERE tb.account_idx = 3 OR tb.account_idx IS NULL " +
            "ORDER  BY tb.account_idx DESC ")
    List<ThemeBookMark> bookMarkList();
}
