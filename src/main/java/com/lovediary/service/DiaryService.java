package com.lovediary.service;

import com.lovediary.dto.AlarmDto;
import com.lovediary.dto.DiaryDto;
import com.lovediary.entity.Alarm;
import com.lovediary.entity.Diary;
import com.lovediary.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * DiaryService
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-29          JJY             최초 등록
 **/
@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // <커플 다이어리 리스트 페이지>
    @Transactional
    public List<DiaryDto> getList() {
        List<Diary> diaryList = diaryRepository.findByAccountIdx(2L);
        List<DiaryDto> resultList = new ArrayList<>();

        for(Diary diary : diaryList) {
            resultList.add(convertToDto(diary));
        }

        return resultList;
    }

    // <커플 다이어리 상세 페이지>
    @Transactional
    public DiaryDto getOne(Long idx) {
        Optional<Diary> wrapper = diaryRepository.findById(idx);
        Diary diary = wrapper.get();

        return convertToDto(diary);
    }

    // <커플 다이어리 작성(저장)>
    @Transactional
    public Long saveItem(DiaryDto diaryDto) {
        return diaryRepository.save(diaryDto.toEntity()).getIdx();
    }

    // Dto 변환
    private DiaryDto convertToDto(Diary diary) {
        return DiaryDto.builder()
                .idx(diary.getIdx())
                .coupleIdx(diary.getCoupleIdx())
                .emotionIdx(diary.getEmotionIdx())
                .categoryIdx(diary.getCategoryIdx())
                .title(diary.getTitle())
                .contents(diary.getContents())
                .accountIdx(diary.getAccountIdx())
                .registDate(diary.getRegistDate())
                .build();
    }
}
