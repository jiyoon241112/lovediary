package com.lovediary.service;

import com.lovediary.dto.BookMarkTheme;
import com.lovediary.dto.BookMarkThemeDto;
import com.lovediary.repository.ThemeBookMarkRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * BookMarkService
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          JJY             최초 등록
 **/
@Service
public class BookMarkService {
    private final ThemeBookMarkRepository themeBookMarkRepository;

    public BookMarkService(ThemeBookMarkRepository themeBookMarkRepository) {
        this.themeBookMarkRepository = themeBookMarkRepository;
    }

    //테마 즐겨찾기 리스트 페이지
    @Transactional
    public List<BookMarkThemeDto> bookMarkList(Long accountIdx) {
        List<BookMarkTheme> themeBookMarkList = themeBookMarkRepository.bookMarkList(accountIdx);
        List<BookMarkThemeDto> resultList = new ArrayList<>();

        for(BookMarkTheme themeBookMark : themeBookMarkList) {
            resultList.add(convertToDto(themeBookMark));
        }

        return resultList;
    }

    //테마 즐겨찾기 DTO 변환
    private BookMarkThemeDto convertToDto(BookMarkTheme themeBookMark) {
        return BookMarkThemeDto.builder()
                .idx(themeBookMark.getIdx())
                .imageIdx(themeBookMark.getImageIdx())
                .name(themeBookMark.getName())
                .themeIdx(themeBookMark.getThemeIdx())
                .build();
    }
}
