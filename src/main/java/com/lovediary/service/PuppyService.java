package com.lovediary.service;

import com.lovediary.dto.PuppyDto;
import com.lovediary.entity.Puppy;
import com.lovediary.repository.PuppyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * 
 * PuppyService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-16
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-16          HTH             최초 등록
 **/
@Service
public class PuppyService {
    private final PuppyRepository puppyRepository;
    public PuppyService(PuppyRepository puppyRepository) {
        this.puppyRepository = puppyRepository;
    }
    
    // 단건 조회
    @Transactional
    public PuppyDto getOne(Long idx) {
        Puppy puppy = puppyRepository.findByCoupleIdx(idx);

        return convertToDto(puppy);
    }

    // 생성, 수정
    @Transactional
    public Long saveItem(PuppyDto puppyDto) {
        return convertToDto(puppyDto.toEntity()).getIdx();
    }

    private PuppyDto convertToDto(Puppy puppy) {
        return PuppyDto.builder()
                .idx(puppy.getIdx())
                .coupleIdx(puppy.getCoupleIdx())
                .name(puppy.getName())
                .condition(puppy.getCondition())
                .hungry(puppy.getHungry())
                .totalWalkDistance(puppy.getTotalWalkDistance())
                .build();
    }
}
