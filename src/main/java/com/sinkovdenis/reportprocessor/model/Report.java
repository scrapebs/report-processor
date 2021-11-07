package com.sinkovdenis.reportprocessor.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class Report {

    private Date creationDate;
    private List<?> reportRows;
    private String emailForResponse;
}
