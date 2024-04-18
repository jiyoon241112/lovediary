package com.lovediary.service;

import com.lovediary.dto.*;
import com.lovediary.entity.Schedule;
import com.lovediary.repository.ScheduleRepository;
import com.lovediary.values.SessionData;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * ScheduleService
 *
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-12
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-12          JJY             최초 등록
 **/
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // <스케줄 리스트 페이지>
    @Transactional
    public List<ScheduleDto> getList(String startDate, SessionData session) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(session.getAccountIdx());
        accountIdx.add(session.getPartnerIdx());

        List<Schedule> scheduleList = scheduleRepository.findByAccountIdxInAndStartDate(accountIdx, startDate);
        List<ScheduleDto> resultList = new ArrayList<>();

        for(Schedule schedule : scheduleList) {
            resultList.add(convertToDto(schedule));
        }

        return resultList;
    }

    // <스케줄 상세 페이지>
    @Transactional
    public ScheduleDto getOne(Long idx) {
        Optional<Schedule> wrapper = scheduleRepository.findById(idx);
        Schedule schedule = wrapper.get();

        return convertToDto(schedule);
    }

    // <스케줄 작성(저장)>
    @Transactional
    public Long saveItem(ScheduleDto scheduleDto) {
        return scheduleRepository.save(scheduleDto.toEntity()).getIdx();
    }

    // Dto 변환
    private ScheduleDto convertToDto(Schedule schedule) {
        return ScheduleDto.builder()
                .idx(schedule.getIdx())
                .bucketIdx(schedule.getBucketIdx())
                .bucketItemIdx(schedule.getBucketItemIdx())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .title(schedule.getTitle())
                .address(schedule.getAddress())
                .addressDetail(schedule.getAddressDetail())
                .latitude(schedule.getLatitude())
                .longitude(schedule.getLongitude())
                .coupleIdx(schedule.getCoupleIdx())
                .accountIdx(schedule.getAccountIdx())
                .deleteYn(schedule.getDeleteYn())
                .registDate(schedule.getRegistDate())
                .modifyDate(schedule.getModifyDate())
                .deleteDate(schedule.getDeleteDate())
                .build();
    }

    // <스케줄 리스트 페이지>
    @Transactional
    public List<CalendarListDto> getCalendarList() {
        List<CalendarList> calendarList = scheduleRepository.getCalendarList();
        List<CalendarListDto> resultList = new ArrayList<>();

        for(CalendarList calendar : calendarList) {
            resultList.add(convertToDto(calendar));
        }

        return resultList;
    }

    // Dto 변환
    private CalendarListDto convertToDto(CalendarList calendar) {
        return CalendarListDto.builder()
                .year(calendar.getYear())
                .month(calendar.getMonth())
                .day(calendar.getDay())
                .build();
    }
}
