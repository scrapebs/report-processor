package com.sinkovdenis.reportprocessor.component;

import com.sinkovdenis.reportprocessor.configuration.MailConfigurationTest;
import com.sinkovdenis.reportprocessor.model.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotifierTest {

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
    private EmailNotifier notifier;

    @Test
    public void testSendReport() {
        doNothing().when(notifier).sendOrThrow(report);
        notifier.sendReport(report);
        verify(notifier).sendOrThrow(report);
    }

    @Test
    public void testSendOrThrow() {
        doReturn(message).when(notifier).buildMessage(report);
        notifier.sendOrThrow(report);
        verify(notifier).buildMessage(report);
        verify(mailSender).send(message);
    }
}
