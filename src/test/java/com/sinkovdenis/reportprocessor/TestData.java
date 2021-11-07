package com.sinkovdenis.reportprocessor;

import com.sinkovdenis.reportprocessor.model.ReportType;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.model.request.ByIdsReportRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestData {

    public static final Date DATE_FROM = Date.from(LocalDateTime.of(2021, 07, 01, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static final Date DATE_TO = Date.from(LocalDateTime.of(2021, 07, 02, 10, 0, 0)
            .atZone(ZoneId.of("UTC")).toInstant());
    public static List<String> REQUESTED_IDS = Arrays.asList("1111", "2222", "3333");
    public static String EMAIL_FOR_RESPONSE = "email@gmail.com";

    public static ByDateReportRequest createByDateReportRequest(ReportType reportType) {
        return ByDateReportRequest.builder()
                .reportType(reportType)
                .from(DATE_FROM)
                .to(DATE_TO)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }

    public static ByIdsReportRequest createByIdsReportRequest(ReportType reportType) {
        return ByIdsReportRequest.builder()
                .reportType(reportType)
                .ids(REQUESTED_IDS)
                .email(EMAIL_FOR_RESPONSE)
                .build();
    }
}