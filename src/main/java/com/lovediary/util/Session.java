package com.lovediary.util;

import com.lovediary.values.SessionData;
import com.lovediary.values.constValues;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * Session
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-18
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-18          HTH             최초 등록
 **/
public class Session {
    // 세션 데이터 획득
    public HttpSession getSessionData(HttpServletRequest request) {
        return request.getSession(true);
    }

    // 로그인 데이터 획득
    public SessionData getLoginData(HttpServletRequest request) {
        HttpSession session = this.getSessionData(request);
        return (SessionData) session.getAttribute(constValues.LOGIN_USER);
    }

    public SessionData getLoginData(HttpSession session) {
        return (SessionData) session.getAttribute(constValues.LOGIN_USER);
    }
}
