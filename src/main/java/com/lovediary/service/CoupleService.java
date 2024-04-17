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

    // 사귄 날짜
    @Transactional
    public Integer getDDay(Long idx) {
        Integer dDay = coupleRepository.findDdayByIdx(idx);

        return dDay;
    }

    // 코드 확인
    @Transactional
    public Long checkCode(String code) {
        Couple couple = coupleRepository.findByCode(code);
        if(couple == null) {
            return null;
        }

        // 2번 체크가 안되도록 코드 삭제
        CoupleDto coupleDto = convertToDto(couple);
        coupleDto.setCode(null);

        return this.saveItem(coupleDto);
    }

    // 랜덤 코드 생성
    public String getCode() {
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        return String.valueOf(generator.nextInt(1000000) % 1000000);
    }

    // DTO 변환
    private CoupleDto convertToDto(Couple couple) {
        return CoupleDto.builder()
                .idx(couple.getIdx())
                .name(couple.getName())
                .startDate(couple.getStartDate())
                .questionTime(couple.getQuestionTime())
                .point(couple.getPoint())
                .code(couple.getCode())
                .deleteYn(couple.getDeleteYn())
                .registDate(couple.getRegistDate())
                .modifyDate(couple.getModifyDate())
                .build();
    }
}
