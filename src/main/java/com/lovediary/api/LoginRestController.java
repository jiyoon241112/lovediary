package com.lovediary.api;

import com.lovediary.dto.AccountDto;
import com.lovediary.service.AccountService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.SessionData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * 
 * LoginRestController
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-29          HTH             최초 등록
 **/
@RestController
public class LoginRestController {
    private final AccountService accountService;
    public LoginRestController(AccountService service) {
        this.accountService = service;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseData login(HttpServletRequest request, AccountDto accountDto) {
        // ID가 없을 때
        if(accountDto.getId() == null || accountDto.getId().isEmpty()) {
            return new ResponseData(constValues.ERROR, "아이디를 입력해주세요.", null);
        }

        // 패스워드가 없을 때
        if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()) {
            return new ResponseData(constValues.ERROR, "패스워드를 입력해주세요.", null);
        }

        // 로그인 정보 조회
        AccountDto account = accountService.getLoginData(accountDto);

        // 로그인 정보가 없을 때
        if(account == null) {
            return new ResponseData(constValues.ERROR, "로그인 정보가 없습니다.", null);
        }

        // 세션 정보 조회
        SessionData sessionData = accountService.getSessionData(account);

        // 기존 로그인 정보 제거
        request.getSession().invalidate();

        // 로그인 정보를 세션에 저장
        HttpSession session = request.getSession(true);
        session.setAttribute(constValues.LOGIN_USER, sessionData);

        return new ResponseData(constValues.DONE, "로그인 했습니다.", null);
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 로그인 정보 제거
        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }

    // 회원가입
    @PostMapping("/join/{page}")
    public ResponseData join(HttpServletRequest request, @PathVariable(name = "page") Integer page, @RequestParam(value = "birth_day", required = false) String birthDay, AccountDto accountDto) {
        HttpSession session = request.getSession(true);

        if(page == 1) {
            // 성별이 없을 때
            if(accountDto.getGender() == null) {
                return new ResponseData(constValues.ERROR, "성별을 선택해주세요.", null);
            }

            // 이름이 없을 때
            if(accountDto.getName() == null || accountDto.getName().isEmpty()) {
                return new ResponseData(constValues.ERROR, "이름을 입력해주세요.", null);
            }
            
            // 닉네임이 없을 때
            if(accountDto.getLoveName() == null || accountDto.getLoveName().isEmpty()) {
                return new ResponseData(constValues.ERROR, "닉네임을 입력해주세요.", null);
            }

            // 전화번호 없을 때
            if(accountDto.getPhoneNumber() == null || accountDto.getPhoneNumber().isEmpty()) {
                return new ResponseData(constValues.ERROR, "휴대폰 번호를 입력해주세요.", null);
            }

            // ID가 없을 때
            if(accountDto.getId() == null || accountDto.getId().isEmpty()) {
                return new ResponseData(constValues.ERROR, "아이디를 입력해주세요.", null);
            } else if(accountService.existsId(accountDto.getId())) {
                return new ResponseData(constValues.ERROR, "중복된 아이디입니다.", null);
            }

            // 패스워드가 없을 때
            if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()) {
                return new ResponseData(constValues.ERROR, "패스워드를 입력해주세요.", null);
            }

            session.setAttribute(constValues.JOIN_DATA, accountDto);
            return new ResponseData(constValues.DONE, "저장했습니다.", null);
        } else if(page == 4) {
            AccountDto sessionData = (AccountDto) session.getAttribute(constValues.JOIN_DATA);
            sessionData.setProfileIdx(accountDto.getProfileIdx());
            sessionData.setMbti(accountDto.getMbti());
            sessionData.setBloodType(accountDto.getBloodType());

            if(birthDay != null && !birthDay.isEmpty()) {
                sessionData.setBirthDay(Date.valueOf(birthDay));
            }

            // 닉네임이 없을 경우 이름으로 설정
            if(accountDto.getLoveName() == null || accountDto.getLoveName().isEmpty()) {
                accountDto.setLoveName(accountDto.getName());
            }

            Long idx = (Long) session.getAttribute(constValues.COUPLE_DATA);
            sessionData.setCoupleIdx(idx);

            accountService.saveItem(sessionData);
        }

        return new ResponseData(constValues.DONE, "회원가입을 완료했습니다.", null);
    }

    // 문자 보내기
    @PostMapping("/send/sms")
    public ResponseData sendSms(HttpServletRequest request, @RequestParam(name = "phone", required = false) String phone) {
        if(phone == null || phone.isEmpty()) {
            return new ResponseData(constValues.ERROR, "전화번호를 입력해주세요.", null);
        }

        String code = accountService.getCode();

        // 코드를 세션에 저장
        HttpSession session = request.getSession(true);
        session.setAttribute(constValues.CODE_DATA, code);

        return new ResponseData(constValues.DONE, "인증번호가 발송되었습니다.", code);
    }

    // 인증번호 확인
    @PostMapping("/check/code")
    public ResponseData checkCode(HttpServletRequest request, @RequestParam(name = "code", required = false) String code) {
        if(code == null || code.isEmpty()) {
            return new ResponseData(constValues.ERROR, "인증번호를 입력해주세요.", null);
        }

        // 코드 확인
        HttpSession session = request.getSession(true);
        if(!code.equals(session.getAttribute(constValues.CODE_DATA))) {
            return new ResponseData(constValues.ERROR, "잘못된 인증번호입니다.", null);
        }

        session.removeAttribute(constValues.CODE_DATA);
        return new ResponseData(constValues.DONE, "인증되었습니다.", null);
    }

    // 인증번호 삭제
    @PostMapping("/remove/code")
    public ResponseData removeCode(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        session.removeAttribute(constValues.CODE_DATA);
        
        return new ResponseData(constValues.DONE, "저장되었습니다.", null);
    }

    // 비밀번호 변경
    @PostMapping("/password/change")
    public void passwordChange() {
        accountService.changePassword();
    }
}
