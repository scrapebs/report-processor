package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.service.ReportProcessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestListenerTest {

    @Mock
    private ReportProcessorService service;

    @Mock
    private ByDateReportRequest request;

    @InjectMocks
    private ReportRequestListener listener;

    @Test
    public void testHandle() {
        listener.handle(request);
        verify(service).process(request);
    }
}
