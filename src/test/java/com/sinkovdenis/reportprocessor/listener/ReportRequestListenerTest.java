package com.sinkovdenis.reportprocessor.listener;

import com.sinkovdenis.reportprocessor.model.request.ByDateReportRequest;
import com.sinkovdenis.reportprocessor.service.ReportProcessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportRequestListenerTest {

    @Mock
    private ReportProcessorService service;

    @Mock
    private ByDateReportRequest request;

    @InjectMocks
    private ReportRequestListener listener;

    @Test
    void testHandle() {
        listener.handle(request);
        verify(service).process(request);
    }
}
