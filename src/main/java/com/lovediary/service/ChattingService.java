package com.lovediary.service;

import com.lovediary.dto.ChattingDto;
import com.lovediary.entity.Chatting;
import com.lovediary.repository.ChattingRepository;
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
    public List<ChattingDto> getList(String date) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(2L);
        accountIdx.add(3L);

        List<Chatting> chattingList = chattingRepository.findByAccountIdxInAndRegistDateOrderByIdxDesc(accountIdx, date);
        List<ChattingDto> resultList = new ArrayList<>();
        
        for(Chatting chatting : chattingList) {
            resultList.add(convertToDto(chatting));
        }
        
        return resultList;
    }

    // 생성
    @Transactional
    public Long saveItem(ChattingDto chattingDto) {
        return chattingRepository.save(chattingDto.toEntity()).getIdx();
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
