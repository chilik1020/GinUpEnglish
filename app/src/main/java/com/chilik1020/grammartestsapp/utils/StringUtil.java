package com.chilik1020.grammartestsapp.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static String getPublicStr(){
        String str1 = AppConstant.strPart1;
        String str2 = AppConstant.strPart2;
        String str3 = AppConstant.strPart3;
        String str4 = AppConstant.strPart4;
        String str5 = AppConstant.strPart5;
        String str = getPart(str1) +
                getPart(str2) +
                getPart(str3) +
                getPart(str4) +
                getPart(str5);
        return str;
    }

    private static String getPart(String s){
        String str = "";
        try {
            byte[] bytes = Base64.decode(s, Base64.DEFAULT);
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
