package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.component.EmailSender;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessor;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateSaleReportProcessor;
import com.sinkovdenis.reportprocessor.configuration.kafka.properties.RequestListenerProperties;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestListenerTest {

    @Mock
    private RequestListenerProperties properties;

    @Mock
    private ReportProcessorResolver reportProcessorResolver;

    @Mock
    private EmailSender emailSender;

    @Mock
    private ByDateReportRequest request;

    @Mock
    private ByDateSaleReportProcessor reportProcessor;

    @Mock
    private Report report;

    @InjectMocks
    private ReportRequestListener listener;

    @Test
    public void testHandle() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.of(reportProcessor));
        when(reportProcessor.process(request)).thenReturn(report);
        listener.handle(request);
        verify(reportProcessorResolver).resolveBy(request);
        verify(reportProcessor).process(request);
        verify(emailSender).sendReport(report);
    }

    @Test
    public void testHandle_exception() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.empty());
        assertThat(catchThrowableOfType(() ->
                        listener.handle(request)
                , IllegalArgumentException.class
        )).isNotNull();
    }
}
