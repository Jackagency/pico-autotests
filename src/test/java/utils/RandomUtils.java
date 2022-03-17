package utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }


    public static int getRandomInt(int min, int max) {
        Random r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    public static Long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomPhone() {
        return getRandomLong(11111111111111111L, 99999999999999999L) + "";
    }

    public static String getRandomPhone(String code) {
        return code + getRandomPhone();
    }

    public static String getDifficultRandomPhone(String code) {
        return code + " (" + getRandomLong(111L, 999L) + ") " + getRandomLong(11111L, 999999L) + "-" + getRandomLong(111L, 999999L);
    }

    public static String getRandomEmail() {
        String emailDomain = "@qa.guru";

        return getRandomString(10) + emailDomain;
    }

    public StringBuilder randomString(){
        Random random = new Random();
        //random word
        StringBuilder word = new StringBuilder(15);
        for (int i = 0; i < 15; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }
        return word;
    }

}