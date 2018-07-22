package com.julialoseva.networker.tools;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class DateFormatter {

    public String getFormattedDate(long timeStamp) {
        PrettyTime prettyTime = new PrettyTime();
        prettyTime.format(
                new Date(
                        timeStamp
                )
        );

        String result = "";
        return result;
    }
}