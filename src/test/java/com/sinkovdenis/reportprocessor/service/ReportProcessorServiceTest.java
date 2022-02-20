package com.sinkovdenis.reportprocessor.service;

import com.sinkovdenis.reportprocessor.component.ReportResultsSender;
import com.sinkovdenis.reportprocessor.component.report.ReportProcessorResolver;
import com.sinkovdenis.reportprocessor.component.report.date.ByDateOrderReportProcessor;
import com.sinkovdenis.reportprocessor.model.ProcessingEvent;
import com.sinkovdenis.reportprocessor.model.Report;
import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.publisher.ProcessingEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.sinkovdenis.reportprocessor.model.ProcessingEvent.ProcessingEventStatus.ERROR;
import static com.sinkovdenis.reportprocessor.model.ProcessingEvent.ProcessingEventStatus.SUCCESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportProcessorServiceTest {
    
    @Spy
    @InjectMocks
    private ReportProcessorService service;
    
    @Mock
    private ByDateOrderReportProcessor reportProcessor;
    @Mock
    private ReportProcessorResolver reportProcessorResolver;
    @Mock
    private ReportResultsSender reportResultsSender;
    @Mock
    private ProcessingEventPublisher eventPublisher;

    @Mock
    private ByDateReportRequest request;
    @Mock
    private ProcessingEvent processingEvent;
    @Mock
    private Report report;

    
    @Test
    public void testProcess() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.of(reportProcessor));
        when(reportProcessor.process(request)).thenReturn(report);
        doReturn(processingEvent).when(service).buildProcessingEvent(anyLong(), any());
        
        service.process(request);
        
        verify(reportProcessorResolver).resolveBy(request);
        verify(reportProcessor).process(request);
        verify(reportResultsSender).sendReport(report);
        verify(service).buildProcessingEvent(anyLong(), eq(SUCCESS));
        verify(eventPublisher).publish(processingEvent);
    }

    @Test
    public void testProcess_requestIsNotSupported() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.empty());
        doReturn(processingEvent).when(service).buildProcessingEvent(anyLong(), any(), anyString());
        
        service.process(request);

        verify(reportProcessorResolver).resolveBy(request);
        verify(reportProcessor, times(0)).process(request);
        verify(service).buildProcessingEvent(anyLong(), eq(ERROR), anyString());
        verify(eventPublisher).publish(processingEvent);
    }

    @Test
    public void testProcess_exception() {
        when(reportProcessorResolver.resolveBy(request)).thenReturn(Optional.of(reportProcessor));
        when(reportProcessor.process(request)).thenThrow(new RuntimeException("exception"));
        doReturn(processingEvent).when(service).buildProcessingEvent(anyLong(), any(), anyString());

        service.process(request);

        verify(reportProcessorResolver).resolveBy(request);
        verify(reportProcessor).process(request);
        verify(service).buildProcessingEvent(anyLong(), eq(ERROR), anyString());
        verify(eventPublisher).publish(processingEvent);
    }
    
    @Test
    public void testBuildProcessingEvent_withoutDetails() {
        doReturn(processingEvent).when(service).buildProcessingEvent(100L, SUCCESS, null);
        assertThat(service.buildProcessingEvent(100L, SUCCESS)).isEqualTo(processingEvent);
        verify(service).buildProcessingEvent(100L, SUCCESS, null);
    }

    @Test
    public void testBuildProcessingEvent() {
        assertThat(service.buildProcessingEvent(100L, ERROR, "message"))
                .isEqualTo(ProcessingEvent.builder()
                        .requestId(100L)
                        .status(ERROR)
                        .details("message")
                        .build());
    }
}
