package com.lovediary.service;

import com.lovediary.dto.CoupleDto;
import com.lovediary.entity.Couple;
import com.lovediary.repository.CoupleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 
 * CoupleService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-30
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-03-30          HTH             최초 등록
 **/
@Service
public class CoupleService {
    private final CoupleRepository coupleRepository;
    public CoupleService(CoupleRepository repository) {
        this.coupleRepository = repository;
    }

    // 단건 조회
    @Transactional
    public CoupleDto getOne(Long idx) {
        Optional<Couple> wrapper = coupleRepository.findById(idx);
        Couple couple = wrapper.get();

        return convertToDto(couple);
    }

    // 생성, 수정
    @Transactional
    public Long saveItem(CoupleDto coupleDto) {
        return coupleRepository.save(coupleDto.toEntity()).getIdx();
    }

    // DTO 변환
    private CoupleDto convertToDto(Couple couple) {
        return CoupleDto.builder()
                .idx(couple.getIdx())
                .name(couple.getName())
                .startDate(couple.getStartDate())
                .questionTime(couple.getQuestionTime())
                .point(couple.getPoint())
                .deleteYn(couple.getDeleteYn())
                .registDate(couple.getRegistDate())
                .modifyDate(couple.getModifyDate())
                .build();
    }
}
