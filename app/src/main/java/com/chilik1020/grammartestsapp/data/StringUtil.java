package com.chilik1020.grammartestsapp.data;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static String getPublicStr(){
        String str1 = "TUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFASDFROEFNSUlCQ2dLQ0FRRUFvYXRTMXZiNlpQT21mQ0FmZGVQTg==";
        String str2 = "K1lBOTEwZXdrMDlwc041WDBReHZKNDY5dASTTU5PcUJMS25La1hVR3pHRjJpQnZnWjBNNEVLSURhU3EvcXV0VGE5bWY3a3VPTEdyNWxaRW1RRA==";
        String str3 = "K2NWYmF4ajZldkd6QlZ0N05kRXJ3Tm1ONGZlbmlwUjZINDZrWFVRc1JtM0ZUc2NFTldmTzlyTkh1alc1NHBabldBb295a0NPTGVPMlNlUlVucw==";
        String str4 = "Q1RNU1laVjE5MFd2Nm5tcXJDWVZXejdZSE5zbGRad1JpN0pTb29qTGdncERiYStTaHBqRXZPVTB0QWhMSUVuM20yL1BJVzdsY0w2MFhNdThWako5YnFabW9vcm9xVC84VENycGg=";
        String str5 = "TE9ITmtkM081TE9ZSGhnSkZuWVdrM2NQdFc3Wjhxdk9ramSDSk9NSmFiRXh5Y21VeXc4V2lZZkRRSURBUUFC";
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
