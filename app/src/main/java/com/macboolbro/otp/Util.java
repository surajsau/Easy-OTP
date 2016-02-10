package com.macboolbro.otp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MacboolBro on 09/02/16.
 */
public class Util implements IConstants {
    public static void showShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String otpFromMessage(String message) {
        Pattern otpStringPattern = Pattern.compile(OTP_STRING_REGEX);
        Pattern otpPattern = Pattern.compile(OTP_REGEX);

        Matcher otpStringMatcher = otpStringPattern.matcher(message);
        if(otpStringMatcher.find()) {
            String otpString = otpStringMatcher.group(0);
            Matcher otpMatcher = otpPattern.matcher(otpString);

            if(otpMatcher.find()) {
                String otp = otpMatcher.group(0);
                return otp;
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public static PendingIntent getClipboardPendingIntent(Context context, String clipText) {
        Intent clipboardIntent = new Intent(COPY_INTENT_FILTER);
        clipboardIntent.putExtra(CLIPBOARD_STRING, clipText);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, CLIPBOARD_REQUEST, clipboardIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }
}