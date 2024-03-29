package com.lovediary.api;

import com.lovediary.dto.AccountDto;
import com.lovediary.service.AccountService;
import com.lovediary.values.ResponseData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {
    private AccountService accountService;
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

        // 기존 로그인 정보 제거
        request.getSession().invalidate();

        // 로그인 정보를 세션에 저장(15분 유지)
        HttpSession session = request.getSession(true);
        session.setAttribute(constValues.LOGIN_USER, account);

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
}
