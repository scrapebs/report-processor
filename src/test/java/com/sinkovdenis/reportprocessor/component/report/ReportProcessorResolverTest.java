package com.sinkovdenis.reportprocessor.component.report;

import com.sinkovdenis.reportprocessor.model.request.GenericReportRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportProcessorResolverTest  {

    @Mock
    private List<ReportProcessor<?>> reportProcessorList;

    @Mock
    private ReportProcessor<?> reportProcessor;

    @Mock
    private GenericReportRequest request;

    @InjectMocks
    private ReportProcessorResolver resolver;

    @Test
    void testResolveBy() {
        when(reportProcessor.support(request)).thenReturn(true);
        when(reportProcessorList.stream()).thenReturn(Stream.of(reportProcessor));
        assertThat(resolver.resolveBy(request)).isEqualTo(Optional.of(reportProcessor));
    }

    @Test
    void testResolveBy_notSupport() {
        when(reportProcessor.support(request)).thenReturn(false);
        when(reportProcessorList.stream()).thenReturn(Stream.of(reportProcessor));
        assertThat(resolver.resolveBy(request)).isNotPresent();
    }
}
