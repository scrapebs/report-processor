package com.sinkovdenis.reportprocessor.component;

import com.sinkovdenis.reportprocessor.model.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender {

    public void sendReport(Report report) {
        log.debug("report sent to {}", report.getEmailForResponse());
    }
}
