package com.sinkovdenis.reportprocessor.component;

import com.sinkovdenis.reportprocessor.configuration.MailConfigurationTest;
import com.sinkovdenis.reportprocessor.model.Report;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportResultsEmailSenderTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MailConfigurationTest mailConfiguration;

    @Mock
    private SimpleMailMessage message;

    @Mock
    private Report report;

    @Spy
    @InjectMocks
    private ReportResultsEmailSender notifier;

    @Test
    void testSendReport() {
        doNothing().when(notifier).sendOrThrow(report);
        notifier.sendReport(report);
        verify(notifier).sendOrThrow(report);
    }

    @Test
    void testSendOrThrow() {
        doReturn(message).when(notifier).buildMessage(report);
        notifier.sendOrThrow(report);
        verify(notifier).buildMessage(report);
        verify(mailSender).send(message);
    }
}
