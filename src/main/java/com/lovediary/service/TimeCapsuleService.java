package com.lovediary.service;

import com.lovediary.dto.TimecapsuleDto;
import com.lovediary.entity.Timecapsule;
import com.lovediary.repository.TimecapsuleRepository;
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
    private final TimecapsuleRepository timeCapsuleRepository;
    public TimeCapsuleService(TimecapsuleRepository timeCapsuleRepository) {
        this.timeCapsuleRepository = timeCapsuleRepository;
    }

    // <타입캡슐 리스트 페이지>
    @Transactional
    public List<TimecapsuleDto> getList() {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(2L);
        accountIdx.add(3L);

        List<Timecapsule> timeCapsuleList = timeCapsuleRepository.findByAccountIdxInOrderByIdxDesc(accountIdx);
        List<TimecapsuleDto> resultList = new ArrayList<>();

        for(Timecapsule timeCapsule : timeCapsuleList) {
            resultList.add(convertToDto(timeCapsule));
        }

        return resultList;
    }

    // <타입캡슐 상세 페이지>
    @Transactional
    public TimecapsuleDto getOne(Long idx) {
        Optional<Timecapsule> wrapper = timeCapsuleRepository.findById(idx);
        Timecapsule timeCapsule = wrapper.get();

        return convertToDto(timeCapsule);
    }

    // <타입캡슐 작성(저장)>
    @Transactional
    public Long saveItem(TimecapsuleDto timeCapsuleDto) {
        return timeCapsuleRepository.save(timeCapsuleDto.toEntity()).getIdx();
    }

    // Dto 변환
    private TimecapsuleDto convertToDto(Timecapsule timeCapsule) {
        String name = null;
        if(timeCapsule.getAccount().getCoupleAccount() == null) {
            name = timeCapsule.getAccount().getName();
        } else {
            name = timeCapsule.getAccount().getCoupleAccount().getLoveName();
        }

        return TimecapsuleDto.builder()
                .idx(timeCapsule.getIdx())
                .openDate(timeCapsule.getOpenDate())
                .title(timeCapsule.getTitle())
                .contents(timeCapsule.getContents())
                .accountIdx(timeCapsule.getAccountIdx())
                .accountName(name)
                .profileIdx(timeCapsule.getAccount().getProfileIdx())
                .deleteYn(timeCapsule.getDeleteYn())
                .registDate(timeCapsule.getRegistDate())
                .modifyDate(timeCapsule.getModifyDate())
                .deleteDate(timeCapsule.getDeleteDate())
                .build();
    }
}
