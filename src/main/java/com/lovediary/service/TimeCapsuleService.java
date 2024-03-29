package com.lovediary.service;

import com.lovediary.dto.DiaryDto;
import com.lovediary.dto.TimeCapsuleDto;
import com.lovediary.entity.Diary;
import com.lovediary.entity.TimeCapsule;
import com.lovediary.repository.TimeCapsuleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * TimeCapsuleService
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
public class TimeCapsuleService {
    private TimeCapsuleRepository timeCapsuleRepository;
    public TimeCapsuleService(TimeCapsuleRepository timeCapsuleRepository) {
        this.timeCapsuleRepository = timeCapsuleRepository;
    }

    // <타입캡슐 리스트 페이지>
    @Transactional
    public List<TimeCapsuleDto> getList() {
        List<TimeCapsule> timeCapsuleList = timeCapsuleRepository.findByAccountIdx(2L);
        List<TimeCapsuleDto> resultList = new ArrayList<>();

        for(TimeCapsule timeCapsule : timeCapsuleList) {
            resultList.add(convertToDto(timeCapsule));
        }

        return resultList;
    }

    // <타입캡슐 상세 페이지>
    @Transactional
    public TimeCapsuleDto getOne(Long idx) {
        Optional<TimeCapsule> wrapper = timeCapsuleRepository.findById(idx);
        TimeCapsule timeCapsule = wrapper.get();

        return convertToDto(timeCapsule);
    }

    // <타입캡슐 작성(저장)>
    @Transactional
    public Long saveItem(TimeCapsuleDto timeCapsuleDto) {
        return timeCapsuleRepository.save(timeCapsuleDto.toEntity()).getIdx();
    }

    // Dto 변환
    private TimeCapsuleDto convertToDto(TimeCapsule timeCapsule) {
        return TimeCapsuleDto.builder()
                .idx(timeCapsule.getIdx())
                .openDate(timeCapsule.getOpenDate())
                .title(timeCapsule.getTitle())
                .contents(timeCapsule.getContents())
                .accountIdx(timeCapsule.getAccountIdx())
                .deleteYn(timeCapsule.getDeleteYn())
                .registDate(timeCapsule.getRegistDate())
                .modifyDate(timeCapsule.getModifyDate())
                .deleteDate(timeCapsule.getDeleteDate())
                .build();
    }
}
