package com.lovediary.service;

import com.lovediary.dto.CoupleAnswerDto;
import com.lovediary.dto.CoupleAnswerReplyDto;
import com.lovediary.entity.CoupleAnswer;
import com.lovediary.entity.CoupleAnswerReply;
import com.lovediary.entity.CoupleQuestion;
import com.lovediary.repository.CoupleAnswerReplyRepository;
import com.lovediary.repository.CoupleAnswerRepository;
import com.lovediary.repository.CoupleQuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 *
 * QuestionService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-02
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-02          HTH             최초 등록
 **/
@Service
public class QuestionService {
    private final CoupleQuestionRepository coupleQuestionRepository;
    private final CoupleAnswerRepository coupleAnswerRepository;
    private final CoupleAnswerReplyRepository coupleAnswerReplyRepository;
    public QuestionService(CoupleQuestionRepository coupleQuestionRepository, CoupleAnswerRepository coupleAnswerRepository, CoupleAnswerReplyRepository coupleAnswerReplyRepository) {
        this.coupleQuestionRepository = coupleQuestionRepository;
        this.coupleAnswerRepository = coupleAnswerRepository;
        this.coupleAnswerReplyRepository = coupleAnswerReplyRepository;
    }

    // 목록 조회
    @Transactional
    public List<CoupleAnswerDto> getList() {
        List<CoupleAnswer> answerList = coupleAnswerRepository.findByCoupleIdxOrderByIdxDesc(1L);
        List<CoupleAnswerDto> resultList = new ArrayList<>();

        for(CoupleAnswer answer : answerList) {
            resultList.add(convertToDto(answer));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public CoupleAnswerDto getOne(Long idx) {
        Optional<CoupleAnswer> wrapper = coupleAnswerRepository.findById(idx);
        CoupleAnswer answer = wrapper.get();

        return convertToDto(answer);
    }

    // 수정
    @Transactional
    public Long saveItem(CoupleAnswerDto answerDto) {
        return coupleAnswerRepository.save(answerDto.toEntity()).getIdx();
    }

    // 오늘의 질문 조회
    @Transactional
    public CoupleAnswerDto getToday() {
        Date today = new Date(new java.util.Date().getTime());

        // 오늘의 질문이 이미 등록되어 있는지 확인
        if(!coupleAnswerRepository.existsByCoupleIdxAndQuestionDate(1L, today)) {
            // 오늘의 질문이 등록되지 않았을 경우 랜덤으로
            Random random = new Random(0);
            Long maxQuestionIdx = coupleQuestionRepository.findIdxMax();

            Long questionIdx = random.nextLong(maxQuestionIdx - 1) + 1;

            Optional<CoupleQuestion> wrapper = coupleQuestionRepository.findById(questionIdx);
            CoupleQuestion question = wrapper.get();

            CoupleAnswer answer = CoupleAnswer.builder()
                    .questionIdx(questionIdx)
                    .questionContents(question.getContents())
                    .questionDate(today)
                    .coupleIdx(1L)
                    .build();

            coupleAnswerRepository.save(answer);
        }

        return convertToDto(coupleAnswerRepository.findByCoupleIdxAndQuestionDate(1L, today));
    }

    // 댓글 조회
    public List<CoupleAnswerReplyDto> getCommentList(Long idx) {
        List<CoupleAnswerReply> commentList = coupleAnswerReplyRepository.findByCoupleAnswerIdxOrderByIdx(idx);
        List<CoupleAnswerReplyDto> resultList = new ArrayList<>();

        for(CoupleAnswerReply comment : commentList) {
            resultList.add(convertToDto(comment));
        }

        return resultList;
    }

    // 댓글 저장
    public Long saveComment(Long idx, String contents) {
        CoupleAnswerReplyDto replyDto = CoupleAnswerReplyDto.builder()
                .coupleAnswerIdx(idx)
                .contents(contents)
                .accountIdx(1L)
                .build();

        return coupleAnswerReplyRepository.save(replyDto.toEntity()).getIdx();
    }

    // DTO 변환
    private CoupleAnswerDto convertToDto(CoupleAnswer coupleAnswer) {
        return CoupleAnswerDto.builder()
                .idx(coupleAnswer.getIdx())
                .questionIdx(coupleAnswer.getQuestionIdx())
                .questionContents(coupleAnswer.getQuestionContents())
                .mansAnswerYn(coupleAnswer.getMansAnswerYn())
                .mansEmotionIdx(coupleAnswer.getMansEmotionIdx())
                .mansAnswerContents(coupleAnswer.getMansAnswerContents())
                .mansAnswerDate(coupleAnswer.getMansAnswerDate())
                .womansAnswerYn(coupleAnswer.getWomansAnswerYn())
                .womansEmotionIdx(coupleAnswer.getWomansEmotionIdx())
                .womansAnswerContents(coupleAnswer.getWomansAnswerContents())
                .womansAnswerDate(coupleAnswer.getWomansAnswerDate())
                .coupleIdx(coupleAnswer.getCoupleIdx())
                .questionDate(coupleAnswer.getQuestionDate())
                .build();
    }

    private CoupleAnswerReplyDto convertToDto(CoupleAnswerReply coupleAnswerReply) {
        return CoupleAnswerReplyDto.builder()
                .idx(coupleAnswerReply.getIdx())
                .coupleAnswerIdx(coupleAnswerReply.getCoupleAnswerIdx())
                .contents(coupleAnswerReply.getContents())
                .accountIdx(coupleAnswerReply.getAccountIdx())
                .deleteYn(coupleAnswerReply.getDeleteYn())
                .registDate(coupleAnswerReply.getRegistDate())
                .modifyDate(coupleAnswerReply.getModifyDate())
                .build();
    }
}
