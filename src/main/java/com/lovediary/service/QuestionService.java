package com.lovediary.service;

import com.lovediary.dto.CoupleAnswerDto;
import com.lovediary.dto.CoupleAnswerReplyDto;
import com.lovediary.dto.EmotionDto;
import com.lovediary.entity.*;
import com.lovediary.repository.*;
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
    private final CoupleAccountRepository coupleAccountRepository;
    private final CoupleQuestionRepository coupleQuestionRepository;
    private final CoupleAnswerRepository coupleAnswerRepository;
    private final CoupleAnswerReplyRepository coupleAnswerReplyRepository;
    private final EmotionRepository emotionRepository;
    public QuestionService(CoupleAccountRepository coupleAccountRepository,
                           CoupleQuestionRepository coupleQuestionRepository,
                           CoupleAnswerRepository coupleAnswerRepository,
                           CoupleAnswerReplyRepository coupleAnswerReplyRepository,
                           EmotionRepository emotionRepository) {
        this.coupleAccountRepository = coupleAccountRepository;
        this.coupleQuestionRepository = coupleQuestionRepository;
        this.coupleAnswerRepository = coupleAnswerRepository;
        this.coupleAnswerReplyRepository = coupleAnswerReplyRepository;
        this.emotionRepository = emotionRepository;
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

        CoupleAnswerDto answerDto = convertToDto(answer);
        Long mansEmotionIdx = answer.getMansEmotionIdx();
        Long womansEmotionIdx = answer.getWomansEmotionIdx();

        if(mansEmotionIdx != null) {
            EmotionDto emotionDto = getEmotionOne(mansEmotionIdx);
            answerDto.setMansImageIdx(emotionDto.getImageIdx());
        }

        if(womansEmotionIdx != null) {
            EmotionDto emotionDto = getEmotionOne(womansEmotionIdx);
            answerDto.setWomansImageIdx(emotionDto.getImageIdx());
        }

        // 계정 정보 조회
        List<CoupleAccount> coupleAccountList = coupleAccountRepository.findByCoupleIdx(answerDto.getCoupleIdx());
        for(CoupleAccount coupleAccount : coupleAccountList) {
            Account account = coupleAccount.getAccount();

            if(coupleAccount.getGender().equals('M')) {
                answerDto.setMansAccountIdx(account.getIdx());
                answerDto.setMansAccountName(coupleAccount.getLoveName());
                answerDto.setMansProfileIdx(account.getProfileIdx());
            } else {
                answerDto.setWomansAccountIdx(account.getIdx());
                answerDto.setWomansAccountName(coupleAccount.getLoveName());
                answerDto.setWomansProfileIdx(account.getProfileIdx());
            }
        }

        return answerDto;
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
    @Transactional
    public List<CoupleAnswerReplyDto> getCommentList(Long idx) {
        List<CoupleAnswerReply> commentList = coupleAnswerReplyRepository.findByCoupleAnswerIdxAndDeleteYnOrderByIdxDesc(idx, 'N');
        List<CoupleAnswerReplyDto> resultList = new ArrayList<>();

        for(CoupleAnswerReply comment : commentList) {
            resultList.add(convertToDto(comment));
        }

        return resultList;
    }

    // 댓글 조회
    @Transactional
    public CoupleAnswerReplyDto getCommentOne(Long idx) {
        Optional<CoupleAnswerReply> wrapper = coupleAnswerReplyRepository.findById(idx);
        CoupleAnswerReply comment = wrapper.get();

        return convertToDto(comment);
    }

    // 댓글 저장
    @Transactional
    public Long saveComment(CoupleAnswerReplyDto replyDto) {
        return coupleAnswerReplyRepository.save(replyDto.toEntity()).getIdx();
    }

    // 기분 조회(목록)
    @Transactional
    public List<EmotionDto> getEmotionList() {
        List<Emotion> emotionList = emotionRepository.findAll();
        List<EmotionDto> resultList = new ArrayList<>();

        for(Emotion emotion : emotionList) {
            resultList.add(convertToDto(emotion));
        }

        return resultList;
    }

    // 기분 조회(단건)
    @Transactional
    public EmotionDto getEmotionOne(Long idx) {
        Optional<Emotion> wrapper = emotionRepository.findById(idx);
        Emotion emotion = wrapper.get();

        return convertToDto(emotion);
    }

    // 오늘의 질문 DTO 변환
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

    // 댓글 DTO 변환
    private CoupleAnswerReplyDto convertToDto(CoupleAnswerReply coupleAnswerReply) {
        String name = null;
        if(coupleAnswerReply.getAccount().getCoupleAccount() == null) {
            name = coupleAnswerReply.getAccount().getName();
        } else {
            name = coupleAnswerReply.getAccount().getCoupleAccount().getLoveName();
        }

        return CoupleAnswerReplyDto.builder()
                .idx(coupleAnswerReply.getIdx())
                .coupleAnswerIdx(coupleAnswerReply.getCoupleAnswerIdx())
                .contents(coupleAnswerReply.getContents())
                .accountIdx(coupleAnswerReply.getAccount().getIdx())
                .accountName(name)
                .profileIdx(coupleAnswerReply.getAccount().getProfileIdx())
                .deleteYn(coupleAnswerReply.getDeleteYn())
                .registDate(coupleAnswerReply.getRegistDate())
                .modifyDate(coupleAnswerReply.getModifyDate())
                .build();
    }

    // 기분 DTO 변환
    private EmotionDto convertToDto(Emotion emotion) {
        return EmotionDto.builder()
                .idx(emotion.getIdx())
                .imageIdx(emotion.getImage().getIdx())
                .build();
    }
}
