package com.lovediary.service;

import com.lovediary.dto.AccountDto;
import com.lovediary.entity.Account;
import com.lovediary.entity.CoupleAccount;
import com.lovediary.repository.AccountRepository;
import com.lovediary.repository.CoupleAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 * AccountService
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
@Service
public class AccountService {
    private final PasswordEncoder encoder;
    private final AccountRepository accountRepository;
    private final CoupleAccountRepository coupleAccountRepository;
    public AccountService(AccountRepository accountRepository, CoupleAccountRepository coupleAccountRepository) {
        this.accountRepository = accountRepository;
        this.coupleAccountRepository = coupleAccountRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    // 목록 조회
    @Transactional
    public List<AccountDto> getList() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> resultList = new ArrayList<>();

        for(Account account : accountList) {
            resultList.add(convertToDto(account));
        }

        return resultList;
    }

    // 단건 조회
    @Transactional
    public AccountDto getOne(Long idx) {
        Optional<Account> wrapper = accountRepository.findById(idx);
        Account account = wrapper.get();

        return convertToDto(account);
    }

    // 수정, 삭제
    @Transactional
    public Long saveItem(AccountDto accountDto) {
        coupleAccountRepository.save(accountDto.toCoupleAccountEntity());
        return accountRepository.save(accountDto.toAccountEntity()).getIdx();
    }

    // 로그인
    public AccountDto getLoginData(AccountDto accountDto) {
        Account account = accountRepository.findByIdAndDeleteYn(accountDto.getId(), 'N');

        if(account != null) {
            if(account.getCoupleAccount() != null && encoder.matches(accountDto.getPassword(), account.getPassword())) {
                return convertToDto(account);
            }
        }

        return null;
    }

    // DTO 변환
    private AccountDto convertToDto(Account account) {
        return convertToDto(account, account.getCoupleAccount());
    }

    // DTO 변환
    private AccountDto convertToDto(Account account, CoupleAccount coupleAccount) {
        AccountDto.AccountDtoBuilder builder = AccountDto.builder().idx(account.getIdx())
                .profileIdx(account.getProfileIdx())
                .type(account.getType())
                .id(account.getId())
                .password(account.getPassword())
                .name(account.getName())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .deleteYn(account.getDeleteYn());

        if(coupleAccount != null) {
            builder.coupleIdx(coupleAccount.getCoupleIdx())
                    .loveName(coupleAccount.getLoveName())
                    .gender(coupleAccount.getGender())
                    .birthDay(coupleAccount.getBirthDay())
                    .mbti(coupleAccount.getMbti())
                    .bloodType(coupleAccount.getBloodType())
                    .naverToken(coupleAccount.getNaverToken())
                    .kakaoToken(coupleAccount.getKakaoToken());
        }

        return builder.build();
    }
}
