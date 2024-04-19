package com.lovediary.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * NaverService
 *
 * @author HTH
 * @version 1.0.0
 * @date 2024-04-19
 * ========================================================
 *  DATE                AUTHOR          NOTE
 * ========================================================
 *  2024-04-19          HTH             최초 등록
 **/
@Service
public class NaverService {
    @Value("${naver.token}")
    private String token;

    @Value("${naver.secret}")
    private String secret;

    @Value("${naver.callback}")
    private String callback;

    // 네이버 로그인 토큰 획득
    public Map<String, Object> getLoginToken(String code, String state) throws Exception {
        String responseToken;
        ObjectMapper mapper = new ObjectMapper();

        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", token)
                .queryParam("client_secret", secret)
                .queryParam("code", code)
                .queryParam("state", URLEncoder.encode(state, StandardCharsets.UTF_8))
                .build();

        // 토큰 획득
        try {
            URL url = new URL(uriComponents.toString());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            responseToken = response.toString();
        } catch (Exception e) {
            throw new Exception(e);
        }

        return mapper.readValue(responseToken, HashMap.class);
    }

    // 네이버 로그인 정보 획득
    public Map<String, Object> getNaverUserInfo(Map<String, Object> params) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String accessToken = params.get("access_token").toString();
            String tokenType = params.get("token_type").toString();

            URL url = new URL("https://openapi.naver.com/v1/nid/me");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", tokenType + " " + accessToken);

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            return mapper.readValue(response.toString(), HashMap.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    // 네이버 로그인 URI
    public String getLoginUri() {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", token)
                .queryParam("redirect_uri", callback)
                .queryParam("state", URLEncoder.encode("love_diary", StandardCharsets.UTF_8))
                .build();

        return uriComponents.toString();
    }
}
