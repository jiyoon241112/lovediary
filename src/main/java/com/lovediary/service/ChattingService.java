package com.lovediary.service;

import com.lovediary.dto.ChattingDto;
import com.lovediary.entity.Chatting;
import com.lovediary.repository.ChattingRepository;
import com.lovediary.values.SessionData;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ChattingService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-15
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-15          HTH             최초 등록
 **/
@Service
public class ChattingService {
    private final ChattingRepository chattingRepository;
    public ChattingService(ChattingRepository chattingRepository) {
        this.chattingRepository = chattingRepository;
    }

    // 목록 조회
    @Transactional
    public List<ChattingDto> getList(String date, SessionData session) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(session.getAccountIdx());
        accountIdx.add(session.getPartnerIdx());

        if(date == null || date.isEmpty()) {
            date = this.getLastDate(date, session);
        }

        // 채팅 기록 없음
        List<ChattingDto> resultList = new ArrayList<>();
        if(date == null || date.isEmpty()) {
            return resultList;
        }

        while(date == null || date.isEmpty() || resultList.size() < 20) {
            List<Chatting> chattingList = chattingRepository.findByAccountIdxInAndRegistDateOrderByIdxDesc(accountIdx, date);

            for(Chatting chatting : chattingList) {
                resultList.add(convertToDto(chatting));
            }

            date = this.getLastDate(date, session);
        }

        return resultList;
    }

    // 생성
    @Transactional
    public Long saveItem(ChattingDto chattingDto) {
        return chattingRepository.save(chattingDto.toEntity()).getIdx();
    }

    // 최근 채팅 일자 조회
    @Transactional
    public String getLastDate(String date, SessionData sessionData) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(sessionData.getAccountIdx());
        accountIdx.add(sessionData.getPartnerIdx());

        return chattingRepository.findByMaxRegistDate(accountIdx, date);
    }
    
    // DTO 변환
    private ChattingDto convertToDto(Chatting chatting) {
        return ChattingDto.builder()
                .idx(chatting.getIdx())
                .emoticonIdx(chatting.getEmoticonIdx())
                .imageIdx(chatting.getImageIdx())
                .contents(chatting.getContents())
                .readYn(chatting.getReadYn())
                .readDate(chatting.getReadDate())
                .accountIdx(chatting.getAccountIdx())
                .accountName(chatting.getAccount().getName())
                .profileIdx(chatting.getAccount().getProfileIdx())
                .deleteYn(chatting.getDeleteYn())
                .registDate(chatting.getRegistDate())
                .deleteDate(chatting.getDeleteDate())
                .build();
    }
}
