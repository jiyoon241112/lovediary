package com.lovediary.service;

import com.lovediary.dto.BalanceAnswerDto;
import com.lovediary.dto.BalanceDto;
import com.lovediary.dto.BalanceItemDto;
import com.lovediary.dto.BalanceReplyDto;
import com.lovediary.entity.Balance;
import com.lovediary.entity.BalanceAnswer;
import com.lovediary.entity.BalanceItem;
import com.lovediary.entity.BalanceReply;
import com.lovediary.repository.BalanceAnswerRepository;
import com.lovediary.repository.BalanceItemRepository;
import com.lovediary.repository.BalanceReplyRepository;
import com.lovediary.repository.BalanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * BalanceService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-07
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-07          HTH             최초 등록
 **/
@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceAnswerRepository balanceAnswerRepository;
    private final BalanceItemRepository balanceItemRepository;
    private final BalanceReplyRepository balanceReplyRepository;
    public BalanceService(BalanceRepository balanceRepository, BalanceAnswerRepository balanceAnswerRepository, BalanceItemRepository balanceItemRepository, BalanceReplyRepository balanceReplyRepository) {
        this.balanceRepository = balanceRepository;
        this.balanceAnswerRepository = balanceAnswerRepository;
        this.balanceItemRepository = balanceItemRepository;
        this.balanceReplyRepository = balanceReplyRepository;
    }

    // 목록 조회
    @Transactional
    public List<BalanceDto> getList(String keyword) {
        List<Balance> balanceList = balanceRepository.findByTitleContainsAndDeleteYnOrderByIdxDesc(keyword, 'N');
        List<BalanceDto> resultList = new ArrayList<>();

        for(Balance balance : balanceList) {
            resultList.add(convertToDto(balance));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public BalanceDto getOne(Long idx) {
        Optional<Balance> wrapper = balanceRepository.findById(idx);
        Balance balance = wrapper.get();

        return convertToDto(balance);
    }

    // 선택지 조회
    @Transactional
    public List<BalanceItemDto> getItemList(Long idx) {
        List<BalanceItem> itemList = balanceItemRepository.findByBalanceIdxOrderByIdx(idx);
        List<BalanceItemDto> resultList = new ArrayList<>();

        for(BalanceItem item : itemList) {
            resultList.add(convertToDto(item));
        }

        return resultList;
    }

    // 댓글 목록 조회
    @Transactional
    public List<BalanceReplyDto> getCommentList(Long idx, Long replyIdx) {
        List<BalanceReply> replyList = balanceReplyRepository.findByBalanceIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(idx, replyIdx, 'N');
        List<BalanceReplyDto> resultList = new ArrayList<>();

        for(BalanceReply reply : replyList) {
            resultList.add(convertToDto(reply));
        }

        return resultList;
    }

    // 댓글 단건 조회
    @Transactional
    public BalanceReplyDto getCommentOne(Long idx) {
        Optional<BalanceReply> wrapper = balanceReplyRepository.findById(idx);
        BalanceReply reply = wrapper.get();

        return convertToDto(reply);
    }

    // 밸런스 게임 DTO 변환
    private BalanceDto convertToDto(Balance balance) {
        return BalanceDto.builder()
                .idx(balance.getIdx())
                .title(balance.getTitle())
                .contents(balance.getContents())
                .accountIdx(balance.getAccountIdx())
                .deleteYn(balance.getDeleteYn())
                .registDate(balance.getRegistDate())
                .modifyDate(balance.getModifyDate())
                .deleteDate(balance.getDeleteDate())
                .build();
    }

    // 선택지 DTO 변환
    private BalanceItemDto convertToDto(BalanceItem item) {
        return BalanceItemDto.builder()
                .idx(item.getIdx())
                .balanceIdx(item.getBalanceIdx())
                .title(item.getTitle())
                .build();
    }

    // 답변 DTO 변환
    private BalanceAnswerDto convertToDto(BalanceAnswer answer) {
        return BalanceAnswerDto.builder()
                .idx(answer.getIdx())
                .balanceIdx(answer.getBalanceIdx())
                .balanceItemIdx(answer.getBalanceItemIdx())
                .accountIdx(answer.getAccountIdx())
                .registDate(answer.getRegistDate())
                .build();
    }

    // 댓글 DTO 변환
    private BalanceReplyDto convertToDto(BalanceReply reply) {
        return BalanceReplyDto.builder()
                .idx(reply.getIdx())
                .balanceIdx(reply.getBalanceIdx())
                .replyIdx(reply.getReplyIdx())
                .contents(reply.getContents())
                .accountIdx(reply.getAccountIdx())
                .deleteYn(reply.getDeleteYn())
                .registDate(reply.getRegistDate())
                .modifyDate(reply.getModifyDate())
                .deleteDate(reply.getDeleteDate())
                .build();
    }
}
