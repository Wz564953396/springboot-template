package com.wz.example.template.container;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final int PASSWORD_LENGTH = 16;
    private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPORSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%&*()_+=<>?/{}~";

    // 生成密妈的方法
    public static String generatePassword() {

        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();
        //生成包合字母、数字和特字符的防机密妈
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int category = random.nextInt(4);
            switch (category) {
                case 0:
                    password.append(LOWER_CASE_CHARACTERS.charAt(random.nextInt(LOWER_CASE_CHARACTERS.length())));
                    break;
                case 1:
                    password.append(UPPER_CASE_CHARACTERS.charAt(random.nextInt(UPPER_CASE_CHARACTERS.length())));
                    break;
                case 2:
                    password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
                    break;
                case 3:
                    password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));
                    break;
            }
        }
        return password.toString();
    }

    public static void main(String[] args) {
        System.out.println(generatePassword());
    }
}
