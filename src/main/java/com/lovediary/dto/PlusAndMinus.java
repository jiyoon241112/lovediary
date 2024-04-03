package com.lovediary.dto;

/**
 * 
 * PlusAndMinus
 * 
 * @author JJY
 * @version 1.0.0
 * @date 2024-04-03
 * ========================================================
 *  DATE                AUTHOR          NOTE 
 * ========================================================
 *  2024-04-03          JJY             최초 등록
 **/
public interface PlusAndMinus {
    Long getPlusAmount();
    Long getMinusAmount();
    Long getTotalAmount();
    int getMon();
}
