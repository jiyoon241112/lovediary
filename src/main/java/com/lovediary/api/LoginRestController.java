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
    public ResponseData join(HttpServletRequest request, @PathVariable(name = "page") Integer page, AccountDto accountDto) {
        HttpSession session = request.getSession(true);

        if(page == 1) {
            // 이름이 없을 때
            if(accountDto.getName() == null || accountDto.getName().isEmpty()) {
                return new ResponseData(constValues.ERROR, "이름을 입력해주세요.", null);
            }

            // ID가 없을 때
            if(accountDto.getId() == null || accountDto.getId().isEmpty()) {
                return new ResponseData(constValues.ERROR, "아이디를 입력해주세요.", null);
            }

            // 패스워드가 없을 때
            if(accountDto.getPassword() == null || accountDto.getPassword().isEmpty()) {
                return new ResponseData(constValues.ERROR, "패스워드를 입력해주세요.", null);
            }

            session.setAttribute(constValues.LOGIN_USER, accountDto);
        } else if(page == 2) {
            AccountDto sessionData = (AccountDto) session.getAttribute(constValues.JOIN_DATA);
            sessionData.setPhoneNumber(accountDto.getPhoneNumber());

            accountService.saveItem(sessionData);
        }

        return new ResponseData(constValues.DONE, "회원가입을 완료했습니다.", null);
    }

    // 비밀번호 변경
    @PostMapping("/password/change")
    public void passwordChange() {
        accountService.changePassword();
    }
}
