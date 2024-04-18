package com.lovediary.service;

import com.lovediary.dto.CommunityReplyDto;
import com.lovediary.dto.DiaryCommentDto;
import com.lovediary.dto.DiaryDto;
import com.lovediary.entity.CommunityReply;
import com.lovediary.entity.Diary;
import com.lovediary.entity.DiaryComment;
import com.lovediary.repository.DiaryCommentRepository;
import com.lovediary.repository.DiaryRepository;
import com.lovediary.values.SessionData;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * DiaryService
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
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final DiaryCommentRepository diaryCommentRepository;
    public DiaryService(DiaryRepository diaryRepository, DiaryCommentRepository diaryCommentRepository) {
        this.diaryRepository = diaryRepository;
        this.diaryCommentRepository = diaryCommentRepository;
    }

    // 커플 다이어리 리스트 페이지
    @Transactional
    public List<DiaryDto> getList(SessionData session) {
        List<Long> accountIdx = new ArrayList<>();
        accountIdx.add(session.getAccountIdx());
        accountIdx.add(session.getPartnerIdx());

        List<Diary> diaryList = diaryRepository.findByAccountIdxInOrderByIdxDesc(accountIdx);
        List<DiaryDto> resultList = new ArrayList<>();

        for(Diary diary : diaryList) {
            resultList.add(convertToDto(diary));
        }

        return resultList;
    }

    // 커플 다이어리 상세 페이지
    @Transactional
    public DiaryDto getOne(Long idx) {
        Optional<Diary> wrapper = diaryRepository.findById(idx);
        Diary diary = wrapper.get();

        return convertToDto(diary);
    }

    // 커플 다이어리 댓글 상세 페이지
    @Transactional
    public List<DiaryCommentDto> getDiaryCommentList(Long idx) {
        List<DiaryComment> commentList = diaryCommentRepository.findByCoupleDiaryIdxAndDeleteYnOrderByIdxDesc(idx,'N');
        List<DiaryCommentDto> resultList = new ArrayList<>();

        for(DiaryComment diaryComment : commentList) {
            resultList.add(convertToDto(diaryComment));
        }

        return resultList;
    }

    // 커플 다이어리 댓글 단건 조회
    @Transactional
    public DiaryCommentDto getDiaryCommentOne(Long idx) {
        Optional<DiaryComment> wrapper = diaryCommentRepository.findById(idx);
        DiaryComment reply = wrapper.get();

        return convertToDto(reply);
    }

    // 커플 다이어리 작성(저장)
    @Transactional
    public Long saveItem(DiaryDto diaryDto) {
        return diaryRepository.save(diaryDto.toEntity()).getIdx();
    }

    // 댓글 저장
    @Transactional
    public Long saveComment(DiaryCommentDto replyDto) {
        return diaryCommentRepository.save(replyDto.toEntity()).getIdx();
    }

    // 일기 Dto 변환
    private DiaryDto convertToDto(Diary diary) {
        return DiaryDto.builder()
                .idx(diary.getIdx())
                .coupleIdx(diary.getCoupleIdx())
                .emotionIdx(diary.getEmotionIdx())
                .title(diary.getTitle())
                .contents(diary.getContents())
                .accountIdx(diary.getAccountIdx())
                .accountName(diary.getAccount().getName())
                .profileIdx(diary.getAccount().getProfileIdx())
                .registDate(diary.getRegistDate())
                .build();
    }

    // 댓글 Dto 변환
    private DiaryCommentDto convertToDto(DiaryComment diaryComment) {
        return DiaryCommentDto.builder()
                .idx(diaryComment.getIdx())
                .coupleDiaryIdx(diaryComment.getCoupleDiaryIdx())
                .contents(diaryComment.getContents())
                .accountIdx(diaryComment.getAccountIdx())
                .accountName(diaryComment.getAccount().getName())
                .profileIdx(diaryComment.getAccount().getProfileIdx())
                .deleteYn(diaryComment.getDeleteYn())
                .registDate(diaryComment.getRegistDate())
                .modifyDate(diaryComment.getModifyDate())
                .deleteDate(diaryComment.getDeleteDate())
                .build();
    }
}
