package com.lovediary.values;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ResponseData
 * 
 * @author HTH
 * @version 1.0.0
 * @date 2024-03-29
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-03-29          HTH             최초 등록
 **/
@Getter
@Setter
@NoArgsConstructor
public class ResponseData {
    private String code;
    private String msg;
    private Object result;

    public Map<String, Object> resultMap() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("msg", msg);
        resultMap.put("data", result);

        return resultMap;
    }

    @Builder
    public ResponseData(String code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
