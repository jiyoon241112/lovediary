package com.lovediary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 
 * CoupleAccount
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoupleAccount {
    @Id @Column(name = "account_idx")
    private Long accountIdx;

    @Column
    private Long coupleIdx;

    @Column(length = 100)
    private String loveName;

    @Column
    private Character gender;

    @Column
    private Date birthDay;

    @Column(length = 4)
    private String mbti;

    @Column(length = 2)
    private String bloodType;

    @Column(length = 100)
    private String naverToken;

    @Column(length = 100)
    private String kakaoToken;

    @Builder
    public CoupleAccount(Long accountIdx, Long coupleIdx, String loveName, Character gender, Date birthDay, String mbti, String bloodType, String naverToken, String kakaoToken) {
        this.accountIdx = accountIdx;
        this.coupleIdx = coupleIdx;
        this.loveName = loveName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.mbti = mbti;
        this.bloodType = bloodType;
        this.naverToken = naverToken;
        this.kakaoToken = kakaoToken;
    }
}
