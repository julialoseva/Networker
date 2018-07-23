package com.julialoseva.networker.tools;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class DateFormatter {

    public String getFormattedDate(long timeStamp) {
        return new PrettyTime()
                .format(
                        new Date(timeStamp)
                );
    }
}