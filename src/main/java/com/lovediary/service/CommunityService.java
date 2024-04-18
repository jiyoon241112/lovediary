package com.lovediary.service;

import com.lovediary.dto.CommunityDto;
import com.lovediary.dto.CommunityReplyDto;
import com.lovediary.entity.Community;
import com.lovediary.entity.CommunityReply;
import com.lovediary.repository.CommunityReplyRepository;
import com.lovediary.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * CommunityService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          HTH             최초 등록
 **/
@Service
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final CommunityReplyRepository communityReplyRepository;
    public CommunityService(CommunityRepository communityRepository, CommunityReplyRepository communityReplyRepository) {
        this.communityRepository = communityRepository;
        this.communityReplyRepository = communityReplyRepository;
    }

    // 목록 조회
    @Transactional
    public List<CommunityDto> getList(String keyword) {
        List<Community> communityList = communityRepository.findByTitleContainsAndDeleteYnOrderByIdxDesc(keyword, 'N');
        List<CommunityDto> resultList = new ArrayList<>();

        for(Community community : communityList) {
            resultList.add(convertToDto(community));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public CommunityDto getOne(Long idx) {
        Optional<Community> wrapper = communityRepository.findById(idx);
        Community community = wrapper.get();

        return convertToDto(community);
    }

    // 생성, 수정
    @Transactional
    public Long saveItem(CommunityDto community) {
        return communityRepository.save(community.toEntity()).getIdx();
    }

    // 댓글 목록 조회
    @Transactional
    public List<CommunityReplyDto> getCommentList(Long idx, Long replyIdx) {
        List<CommunityReply> replyList = communityReplyRepository.findByCommunityIdxAndReplyIdxAndDeleteYnOrderByIdxDesc(idx, replyIdx, 'N');
        List<CommunityReplyDto> resultList = new ArrayList<>();

        for(CommunityReply reply : replyList) {
            resultList.add(convertToDto(reply));
        }

        return resultList;
    }

    // 댓글 단건 조회
    @Transactional
    public CommunityReplyDto getCommentOne(Long idx) {
        Optional<CommunityReply> wrapper = communityReplyRepository.findById(idx);
        CommunityReply reply = wrapper.get();

        return convertToDto(reply);
    }

    // 댓글 저장
    @Transactional
    public Long saveComment(CommunityReplyDto replyDto) {
        return communityReplyRepository.save(replyDto.toEntity()).getIdx();
    }
    
    // 커뮤니티 DTO 변환
    private CommunityDto convertToDto(Community community) {
        String name = null;
        if(community.getAccount().getCoupleAccount() == null) {
            name = community.getAccount().getName();
        } else {
            name = community.getAccount().getCoupleAccount().getLoveName();
        }

        return CommunityDto.builder()
                .idx(community.getIdx())
                .title(community.getTitle())
                .contents(community.getContents())
                .accountIdx(community.getAccount().getIdx())
                .accountName(name)
                .profileIdx(community.getAccount().getProfileIdx())
                .deleteYn(community.getDeleteYn())
                .registDate(community.getRegistDate())
                .modifyDate(community.getModifyDate())
                .deleteDate(community.getDeleteDate())
                .build();
    }

    // 댓글 DTO 변환
    private CommunityReplyDto convertToDto(CommunityReply reply) {
        String name = null;
        if(reply.getAccount().getCoupleAccount() == null) {
            name = reply.getAccount().getName();
        } else {
            name = reply.getAccount().getCoupleAccount().getLoveName();
        }

        return CommunityReplyDto.builder()
                .idx(reply.getIdx())
                .communityIdx(reply.getCommunityIdx())
                .replyIdx(reply.getReplyIdx())
                .contents(reply.getContents())
                .accountIdx(reply.getAccount().getIdx())
                .accountName(name)
                .profileIdx(reply.getAccount().getProfileIdx())
                .deleteYn(reply.getDeleteYn())
                .registDate(reply.getRegistDate())
                .modifyDate(reply.getModifyDate())
                .deleteDate(reply.getDeleteDate())
                .build();
    }
}
