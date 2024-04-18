package com.lovediary.service;

import com.lovediary.dto.AlarmDto;
import com.lovediary.entity.Alarm;
import com.lovediary.repository.AlarmRepository;
import com.lovediary.values.SessionData;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * AlarmService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-26
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-26          HTH             최초 등록
 **/
@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;
    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    // 목록 조회
    @Transactional
    public List<AlarmDto> getList(SessionData session) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(session.getAccountIdx());
        accountIdx.add(session.getPartnerIdx());

        List<Alarm> alarmList = alarmRepository.findByAccountIdxInOrderByIdxDesc(accountIdx);
        List<AlarmDto> resultList = new ArrayList<>();

        for(Alarm alarm : alarmList) {
            resultList.add(convertToDto(alarm));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public AlarmDto getOne(Long idx) {
        Optional<Alarm> wrapper = alarmRepository.findById(idx);
        Alarm alarm = wrapper.get();

        return convertToDto(alarm);
    }

    // 생성, 수정
    @Transactional
    public Long saveItem(AlarmDto alarmDto) {
        return alarmRepository.save(alarmDto.toEntity()).getIdx();
    }

    // DTO 변환
    private AlarmDto convertToDto(Alarm alarm) {
        return AlarmDto.builder()
                .idx(alarm.getIdx())
                .contents(alarm.getContents())
                .link(alarm.getLink())
                .readYn(alarm.getReadYn())
                .readDate(alarm.getReadDate())
                .accountIdx(alarm.getAccountIdx())
                .registDate(alarm.getRegistDate())
                .build();
    }
}
