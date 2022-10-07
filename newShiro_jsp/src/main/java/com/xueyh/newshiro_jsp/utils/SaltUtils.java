package com.xueyh.newshiro_jsp.utils;

import java.util.Random;

public class SaltUtils {

    /**
     * 生成salt
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(4);
        System.out.println(salt);

    }
}
