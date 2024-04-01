package com.lovediary.service;
/**
 * 
 * HouseholdLedgerService
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-29          JJY             최초 등록
 **/
import com.lovediary.dto.HouseholdLedgerDto;
import com.lovediary.dto.TimeCapsuleDto;
import com.lovediary.entity.HouseholdLedger;
import com.lovediary.entity.TimeCapsule;
import com.lovediary.repository.HouseholdLedgerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseholdLedgerService {
    private final HouseholdLedgerRepository householdLedgerRepository;
    public HouseholdLedgerService(HouseholdLedgerRepository householdLedgerRepository) {
        this.householdLedgerRepository = householdLedgerRepository;
    }

    // <가계부 리스트 페이지>
    @Transactional
    public List<HouseholdLedgerDto> getList() {
        List<HouseholdLedger> householdLedgerList = householdLedgerRepository.findByAccountIdx(2L);
        List<HouseholdLedgerDto> resultList = new ArrayList<>();

        for(HouseholdLedger householdLedger : householdLedgerList) {
            resultList.add(convertToDto(householdLedger));
        }

        return resultList;
    }

    // <가계부 상세 페이지>
    @Transactional
    public HouseholdLedgerDto getOne(Long idx) {
        Optional<HouseholdLedger> wrapper = householdLedgerRepository.findById(idx);
        HouseholdLedger householdLedger = wrapper.get();

        return convertToDto(householdLedger);
    }

    // <가계부 작성(저장)>
    @Transactional
    public Long saveItem(HouseholdLedgerDto householdLedgerDto) {
        return householdLedgerRepository.save(householdLedgerDto.toEntity()).getIdx();
    }

    // Dto 변환
    private HouseholdLedgerDto convertToDto(HouseholdLedger householdLedger) {
        return HouseholdLedgerDto.builder()
                .idx(householdLedger.getIdx())
                .categoryIdx(householdLedger.getCategoryIdx())
                .contents(householdLedger.getContents())
                .amount(householdLedger.getAmount())
                .type(householdLedger.getType())
                .dueDate(householdLedger.getDueDate())
                .accountIdx(householdLedger.getAccountIdx())
                .deleteYn(householdLedger.getDeleteYn())
                .registDate(householdLedger.getRegistDate())
                .modifyDate(householdLedger.getModifyDate())
                .deleteDate(householdLedger.getDeleteDate())
                .build();
    }
}
