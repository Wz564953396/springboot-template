package com.wz.example.template.service.impl;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {

    /**
     * Checks if given ID number is valid.
     *
     * @param idNumber ID number to check.
     * @return true if the given ID number is valid, false otherwise
     */
public static boolean isIDNumberValid(String idNumber) {
    String regex = "^\\d{17}[\\dxX]$";
    return idNumber.matches(regex);
}
}
