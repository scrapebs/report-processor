package com.sinkovdenis.reportprocessor.component;

import com.sinkovdenis.reportprocessor.configuration.MailConfiguration;
import com.sinkovdenis.reportprocessor.model.Report;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportResultsEmailSender implements ReportResultsSender {

    private final JavaMailSender mailSender;
    private final MailConfiguration mailConfiguration;

    public void sendReport(Report report) {
        sendOrThrow(report);
        log.debug("Report sent to {}", report.getEmailForResponse());
    }

    void sendOrThrow(Report report) {
        try {
            mailSender.send(buildMessage(report));
        } catch (Exception e) {
            log.warn("Failed to send report to: {}", report.getEmailForResponse(), e);
        }
    }

    SimpleMailMessage buildMessage(Report report) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(report.getReportRows().stream().map(Object::toString).collect(Collectors.joining("\n")));
        message.setSubject("Report successfully processed");
        message.setTo(report.getEmailForResponse());
        message.setFrom(mailConfiguration.getUsername());
        return message;
    }
}
