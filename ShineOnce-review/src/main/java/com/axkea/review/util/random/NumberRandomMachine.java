package com.axkea.review.util.random;

import java.security.SecureRandom;

/**
 * @author Genius
 * @date 2023/10/26 23:13
 **/
public class NumberRandomMachine implements RandomMachine<Long>{
    @Override
    public Long random() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[5]; // 生成 5 个字节的随机数
        secureRandom.nextBytes(randomBytes);
        long randomNumber = 0;
        for (byte b : randomBytes) {
            randomNumber = (randomNumber << 8) + (b & 0xff);
        }
        return Math.abs(randomNumber); // 取绝对值
    }
}
