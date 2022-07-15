package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args)  {
        byte b = 1;
        short s = 10;
        int i = 100;
        long l = 1000L;
        float f = 0.1f;
        double d = 0.01;
        char c = "a".charAt(0);
        String s1 = "string";
        LOG.debug("byte : {}, short : {}, : int : {}, : long : {}, float : {}, double : {}, char : {}, string : {}",
                b, s, i, l, f, d, c, s1);



    }
}