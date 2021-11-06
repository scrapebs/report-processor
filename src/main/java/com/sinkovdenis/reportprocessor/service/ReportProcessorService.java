package com.sinkovdenis.reportprocessor.service;

import com.sinkovdenis.reportprocessor.model.GenericReportRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReportProcessorService {

    public void process(GenericReportRequest request) {
        log.debug("Received request " + request.toString());
    }
}
