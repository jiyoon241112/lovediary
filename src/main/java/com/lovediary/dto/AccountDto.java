package com.lovediary.dto;

import com.lovediary.entity.Account;
import com.lovediary.entity.CoupleAccount;
import lombok.*;

import java.sql.Date;

/**
 * 
 * AccountDto
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-27
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-27          HTH             최초 등록
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountDto {
    private Long idx;
    private Long coupleIdx;
    private Long profileIdx;
    private Character type;
    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String loveName;
    private Character gender;
    private Date birthDay;
    private String mbti;
    private String bloodType;
    private String naverToken;
    private String kakaoToken;
    private Character deleteYn;

    public Account toAccountEntity() {
        return Account.builder()
                .idx(idx)
                .profileIdx(profileIdx)
                .type(type)
                .id(id)
                .password(password)
                .name(name)
                .email(email)
                .phoneNumber(phoneNumber)
                .deleteYn(deleteYn)
                .build();
    }

    public CoupleAccount toCoupleAccountEntity() {
        return CoupleAccount.builder()
                .accountIdx(idx)
                .coupleIdx(coupleIdx)
                .loveName(loveName)
                .gender(gender)
                .birthDay(birthDay)
                .mbti(mbti)
                .bloodType(bloodType)
                .naverToken(naverToken)
                .kakaoToken(kakaoToken)
                .build();
    }

    @Builder
    public AccountDto(Long idx, Long coupleIdx, Long profileIdx, Character type, String id, String password, String name, String email, String phoneNumber, String loveName, Character gender, Date birthDay, String mbti, String bloodType, String naverToken, String kakaoToken, Character deleteYn) {
        this.idx = idx;
        this.coupleIdx = coupleIdx;
        this.profileIdx = profileIdx;
        this.type = type;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.loveName = loveName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.mbti = mbti;
        this.bloodType = bloodType;
        this.naverToken = naverToken;
        this.kakaoToken = kakaoToken;
        this.deleteYn = deleteYn;
    }
}
